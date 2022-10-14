package com.fatec.sul.appdoaluno.util

class DataHora {
    companion object{
        fun minutesOf(tempo: String): Int{
            return tempo.split(":")[1].toInt() + tempo.split(":")[0].toInt() * 60
        }

        fun maiorHoraMinuto(hora1: String, hora2: String): String{
            val hora1Hora = hora1.split("-")[0].split(":")[0].toInt()
            val hora1Minutos = hora1.split("-")[0].split(":")[1].toInt()

            val hora2Hora = hora2.split("-")[0].split(":")[0].toInt()
            val hora2Minutos = hora2.split("-")[0].split(":")[1].toInt()

            when {
                hora1Hora == hora2Hora -> {
                    return when {
                        hora1Minutos == hora2Minutos -> {
                            hora1
                        }
                        hora1Minutos > hora2Minutos -> {
                            hora1
                        }
                        else -> {
                            hora2
                        }
                    }
                }
                hora1Hora > hora2Hora -> {
                    return hora1
                }
                else -> {
                    return hora2
                }
            }
        }

        fun menorHoraMinuto(hora1: String, hora2: String): String{
            val hora1Hora = hora1.split("-")[0].split(":")[0].toInt()
            val hora1Minutos = hora1.split("-")[0].split(":")[1].toInt()

            val hora2Hora = hora2.split("-")[0].split(":")[0].toInt()
            val hora2Minutos = hora2.split("-")[0].split(":")[1].toInt()

            when {
                hora1Hora == hora2Hora -> {
                    return when {
                        hora1Minutos == hora2Minutos -> {
                            hora1
                        }
                        hora1Minutos < hora2Minutos -> {
                            hora1
                        }
                        else -> {
                            hora2
                        }
                    }
                }
                hora1Hora < hora2Hora -> {
                    return hora1
                }
                else -> {
                    return hora2
                }
            }
        }

        fun diaSemana(grid: String): String{
            val i = grid[grid.indexOf("Grid") + "Grid".length]

            if(!i.isDigit()){
                return ""
            }

            return when(i.digitToInt()){
                2 -> return "Segunda-Feira"
                3 -> return "Terça-Feira"
                4 -> return "Quarta-Feira"
                5 -> return "Quinta-Feira"
                6 -> return "Sexta-Feira"
                7 -> return "Sábado"
                else -> {""}
            }
        }
    }
}