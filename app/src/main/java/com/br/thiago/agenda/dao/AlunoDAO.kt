package com.br.thiago.agenda.dao

import com.br.thiago.agenda.modelo.Aluno

class AlunoDAO() {

    companion object {
        val alunos = mutableListOf<Aluno>()
    }

    fun salva(novoAluno: Aluno) {
        alunos.add(novoAluno)
    }

    fun todos() = mutableListOf(alunos)

}
