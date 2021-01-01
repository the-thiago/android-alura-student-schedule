package com.br.thiago.agenda.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.br.thiago.agenda.R
import com.br.thiago.agenda.dao.AlunoDAO
import com.br.thiago.agenda.modelo.Aluno

class FormularioAlunoActivity : AppCompatActivity() {

    val tituloAppBar = "Novo Aluno"
    val dao = AlunoDAO()
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
        title = tituloAppBar
        configurarBotaoSalvar()
    }

    private fun criarAluno(): Aluno {
        val nome = campoNome.text.toString()
        val email = campoEmail.text.toString()
        val telefone = campoTelefone.text.toString()
        return Aluno(nome, telefone, email)
    }

    private fun configurarBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.activity_formulario_aluno_botao_salvar)
        botaoSalvar.setOnClickListener {
            dao.salva(criarAluno())
            Toast.makeText(this, "Aluno Salvo!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

}
