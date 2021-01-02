package com.br.thiago.agenda.dao

import com.br.thiago.agenda.modelo.Aluno

class AlunoDAO() {

    companion object {
        private val alunos = mutableListOf<Aluno>()
        private var contadorID = 1
    }

    fun salva(novoAluno: Aluno?) {
        novoAluno?.id = contadorID
        contadorID++
        novoAluno?.let { alunos.add(it) }
    }

    fun editarAluno(aluno: Aluno){
        var alunoEncontrado : Aluno? = null
        alunos.forEach {
            if (it.id == aluno.id)
                alunoEncontrado = it
        }
        if (alunoEncontrado != null){
            val posicaoDoAluno = alunos.indexOf(alunoEncontrado!!)
            alunos.set(posicaoDoAluno, aluno)
        }
    }

    fun todos(): MutableList<Aluno> {
        val copia = mutableListOf<Aluno>()
        alunos.forEach {
            copia.add(it)
        }
        return copia
    }

    fun remover(aluno: Aluno) {
        var alunoEncontrado : Aluno? = null
        alunos.forEach {
            if (it.id == aluno.id)
                alunoEncontrado = it
        }
        if (alunoEncontrado != null)
            alunos.remove(alunoEncontrado!!)
    }

}
