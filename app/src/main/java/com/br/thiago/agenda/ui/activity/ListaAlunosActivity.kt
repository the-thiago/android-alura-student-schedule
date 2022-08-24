package com.br.thiago.agenda.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.br.thiago.agenda.R
import com.br.thiago.agenda.dao.AlunoDAO
import com.br.thiago.agenda.modelo.Aluno
import com.br.thiago.agenda.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.Serializable

class ListaAlunosActivity() : AppCompatActivity() {

    private val tituloAppBar = "Student List"
    val dao = AlunoDAO()

    private val adapter: ArrayAdapter<Aluno>
        get() {
            val adapter: ArrayAdapter<Aluno> = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    dao.todos()
            )
            return adapter
        }

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

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.activity_lista_alunos_menu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_lista_alunos_menu_remover) {
            val menuInfo = item.menuInfo as AdapterView.AdapterContextMenuInfo
            val alunoEscolhido = adapter.getItem(menuInfo.position) as Aluno
            dao.remover(alunoEscolhido)
            configurarLista()
        }
        return super.onContextItemSelected(item)
    }

    private fun configurarLista() {
        val listaDeAlunos = findViewById<ListView>(R.id.activity_lista_alunos_listview)
        configurarAdapter(listaDeAlunos)
        configurarListenerDeCliquePorItem(listaDeAlunos)
        registerForContextMenu(listaDeAlunos)
    }

    private fun configurarListenerDeCliquePorItem(listaDeAlunos: ListView) {
        listaDeAlunos.setOnItemClickListener { adapterView, view, posicao, id ->
            val alunoEscolhido = adapterView.getItemAtPosition(posicao) as Aluno
            abreFormularioParaEditarAluno(alunoEscolhido)
        }
    }

    private fun abreFormularioParaEditarAluno(alunoEscolhido: Aluno) {
        val vaiParaFormularioAlunoActivity = Intent(this, FormularioAlunoActivity::class.java)
        vaiParaFormularioAlunoActivity.putExtra(CHAVE_ALUNO, alunoEscolhido as Serializable)
        startActivity(vaiParaFormularioAlunoActivity)
    }

    private fun configurarAdapter(listaDeAlunos: ListView) {
        listaDeAlunos.adapter = this.adapter
    }

    private fun configurarFABNovoAluno() {
        val botaoNovoAuno =
                findViewById<FloatingActionButton>(R.id.activity_lista_alunos_fab_novo_aluno)
        botaoNovoAuno.setOnClickListener {
            startActivity(Intent(this, FormularioAlunoActivity::class.java))
        }
    }

}
