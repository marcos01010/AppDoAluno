package com.fatec.sul.appdoaluno.util

import com.fatec.sul.appdoaluno.model.Aluno
import com.fatec.sul.appdoaluno.model.Horario
import com.fatec.sul.appdoaluno.model.Materia
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.util.regex.Pattern

class Document{
    companion object{
        fun getProfessor(html: String) :List<List<String>>{
            var dadosProfessores: List<List<String>> = listOf()
            val matcher = Pattern.compile("\\[[^!]*]]").matcher(html)
            while (matcher.find()){
                val json = matcher.group().split("'><")[0]

                dadosProfessores = GsonBuilder()
                    .create()
                    .fromJson(json, object : TypeToken<List<List<String>>>(){}.type)
            }

            return dadosProfessores
        }

        fun getHoraio(html: String) :ArrayList<Horario>{
            var horarios: List<List<List<String>>> = arrayListOf()
            val matcher = Pattern.compile("<input type=\"hidden\" name=\"Grid[0-9]ContainerDataV\" [^>]*>").matcher(html)
            var json = "["
            while (matcher.find()){
                var data = matcher.group()
                val diaSemana = DataHora.diaSemana(data)

                data = data.substring(data.indexOf("'[[") + 1,data.indexOf("]]'") + 2)

                if(data.contains("Resources/Portuguese")){
                    if(json.length > 1){
                        json += ","
                    }
                    data = data.replace("Resources/Portuguese/GeneXusX/Vazio.gif", diaSemana)
                    json += data
                }
            }
            json += "]"

            horarios = (GsonBuilder()
                .create()
                .fromJson(json, object : TypeToken<List<List<List<String>>>>(){}.type))

            var siglas = hashSetOf<String>()
            var horariosFormatado = arrayListOf<Horario>()
            horarios.forEach { listaListaDados ->
                listaListaDados.forEach{ listaDados->
                    siglas.add(listaDados[2])
                }

                siglas.forEach{ sigla ->
                    val horario = Horario()
                    var inicio = ""
                    var fim = ""
                    listaListaDados.filter {listaDados->
                        listaDados[2] == sigla
                    }.forEach { listaDados->
                        val hora = listaDados[1].split("-")
                        horario.sigla = listaDados[2]
                        horario.diaDaSemana = listaDados[0]

                        fim = if(fim.isBlank()){
                            hora[1]
                        }else{
                            DataHora.maiorHoraMinuto(hora[1], fim)
                        }

                        inicio = if(inicio.isBlank()){
                            hora[0]
                        }else{
                            DataHora.menorHoraMinuto(hora[0], inicio)
                        }

                    }
                    horario.hora = "$inicio-$fim"
                    horariosFormatado.add(horario)
                }
                siglas = hashSetOf()
            }

            return horariosFormatado
        }

        fun getMaterias(html: String): List<Materia>{
            var materias: List<Materia> = listOf()
            val matcher = Pattern.compile("(\"vALU_ALUNOHISTORICO_SDT\":\\[)[^]]*]").matcher(html)
            while (matcher.find()){
                val json = matcher.group().replace("\"vALU_ALUNOHISTORICO_SDT\":","")
                materias = GsonBuilder()
                    .create()
                    .fromJson(json, object : TypeToken<List<Materia>>(){}.type)
            }
            return materias
        }

        fun getDados(html: String): Aluno? {
            var aluno : Aluno? = null
            val matcher = Pattern.compile("(\\{\"_EventName\":)[^}]*[}]").matcher(html)
            while (matcher.find()){
                val json = removeFiledVTexto(matcher.group())
                aluno = GsonBuilder()
                    .create()
                    .fromJson(json, Aluno::class.java)
                    .apply {
                        this.nome = this.nome.replace(" -", "")
                    }
            }
            return aluno
        }

        private fun getAjaxKey(html: String): String{
            val matcher = Pattern.compile("(\"GX_AJAX_KEY\":\"[^\"]*\")").matcher(html)
            var ajaxKey = ""
            while (matcher.find()){
                ajaxKey = matcher.group()
            }
            return ajaxKey
        }

        private fun getAjaxToken(html: String): String{
            val matcher = Pattern.compile("(\"AJAX_SECURITY_TOKEN\":\"[^\"]*\")").matcher(html)
            var ajaxToken = ""
            while (matcher.find()){
                ajaxToken = matcher.group()
            }
            return ajaxToken
        }

        fun getBaseGx(html: String): String{
            return jsonBase.replace("\"GX_AJAX_KEY\":\"\"", getAjaxKey(html))
                .replace("\"AJAX_SECURITY_TOKEN\":\"\"", getAjaxToken(html))
        }

        private fun removeFiledVTexto(json: String):String{
            val s = json.replace(json.substring(json.indexOf("\"vTEXTO\""),json.indexOf("\"vPRO_PESSOALNOME\"")), "")
            return s.substring(0, s.indexOf(",\"vTREENODECOLLECTIONDATA_MPAGE\":")) + "}"
        }

        private const val jsonBase = "{\n" +
                "   \"_EventName\":\"E'EVT_CONFIRMAR'.\",\n" +
                "   \"_EventGridId\":\"\",\n" +
                "   \"_EventRowId\":\"\",\n" +
                "   \"MPW0005_CMPPGM\":\"login_top.aspx\",\n" +
                "   \"MPW0005GX_FocusControl\":\"\",\n" +
                "   \"vSAIDA\":\"\",\n" +
                "   \"vREC_SIS_USUARIOID\":\"\",\n" +
                "   \"GX_FocusControl\":\"vSIS_USUARIOID\",\n" +
                "   \"GX_AJAX_KEY\":\"\",\n" +
                "   \"AJAX_SECURITY_TOKEN\":\"\",\n" +
                "   \"GX_CMP_OBJS\":{\n" +
                "      \"MPW0005\":\"login_top\"\n" +
                "   },\n" +
                "   \"sCallerURL\":\"http://siga.cps.sp.gov.br/home.aspx\",\n" +
                "   \"GX_RES_PROVIDER\":\"GXResourceProvider.aspx\",\n" +
                "   \"GX_THEME\":\"GeneXusX\",\n" +
                "   \"_MODE\":\"\",\n" +
                "   \"Mode\":\"\",\n" +
                "   \"IsModified\":\"1\"\n" +
                "}"
    }
}