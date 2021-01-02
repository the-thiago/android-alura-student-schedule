package com.br.thiago.agenda.modelo

import java.io.Serializable

class Aluno(
        var nome: String,
        var telefone: String,
        var email: String
) : Serializable {

    var id: Int = 0

    override fun toString(): String {
        return nome
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Aluno

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id
    }

}
