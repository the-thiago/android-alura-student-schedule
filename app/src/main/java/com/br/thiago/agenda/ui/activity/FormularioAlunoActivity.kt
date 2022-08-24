package com.br.thiago.agenda.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.br.thiago.agenda.R
import com.br.thiago.agenda.dao.AlunoDAO
import com.br.thiago.agenda.modelo.Aluno
import com.br.thiago.agenda.ui.activity.ConstantesActivities.Companion.CHAVE_ALUNO

class FormularioAlunoActivity : AppCompatActivity() {

    private val tituloAppBarNovoALuno = "New Student"
    private val tituloAppBarEditarALuno = "Edit Student"
    val dao = AlunoDAO()
    var aluno: Aluno? = null
    private val campoNome: EditText
        get() {
            val campoNome = findViewById<EditText>(R.id.activity_formulario_aluno_nome)
            return campoNome
        }

    private val campoEmail: EditText
        get() {
            val campoEmail = findViewById<EditText>(R.id.activity_formulario_aluno_email)
            return campoEmail
        }

    private val campoTelefone: EditText
        get() {
            val campoTelefone = findViewById<EditText>(R.id.activity_formulario_aluno_telefone)
            return campoTelefone
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario_aluno)
        title = tituloAppBarNovoALuno
        carregarEPreenhcerAluno()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_formulario_aluno_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.activity_formulario_aluno_menu_salvar) {
            finalizarFormulario()
//            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun carregarEPreenhcerAluno() {
        aluno = intent.getSerializableExtra(CHAVE_ALUNO) as Aluno?
        aluno?.let { title = tituloAppBarEditarALuno }
        campoNome.setText(aluno?.nome)
        campoEmail.setText(aluno?.email)
        campoTelefone.setText(aluno?.telefone)
    }

    private fun preencherAluno() {
        aluno?.nome = campoNome.text.toString()
        aluno?.email = campoEmail.text.toString()
        aluno?.telefone = campoTelefone.text.toString()
    }

    private fun finalizarFormulario() {
        if (aluno == null) {
            aluno = Aluno("", "", "")
            dao.salva(aluno)
            preencherAluno()
        } else {
            preencherAluno()
            aluno?.let { aluno -> dao.editarAluno(aluno) }
        }
        finish()
    }

}
