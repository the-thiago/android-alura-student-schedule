package com.br.thiago.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.br.thiago.agenda.R
import com.br.thiago.agenda.dao.AlunoDAO
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListaAlunosActivity() : AppCompatActivity() {

    val dao = AlunoDAO()
    val tituloAppBar = "Lista de alunos"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_alunos)
        title = tituloAppBar

        configurarFABNovoAluno()
    }

    override fun onResume() {
        super.onResume()
        configurarLista()

    }

    private fun configurarLista() {
        val listaDeAlunos = findViewById<ListView>(R.id.activity_lista_alunos_listview)
        dao.todos().forEach { aluno ->
            listaDeAlunos.adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    aluno
            )
        }
    }

    private fun configurarFABNovoAluno() {
        val botaoNovoAuno =
                findViewById<FloatingActionButton>(R.id.activity_lista_alunos_fab_novo_aluno)
        botaoNovoAuno.setOnClickListener {
            startActivity(Intent(this, FormularioAlunoActivity::class.java))
        }
    }

}
