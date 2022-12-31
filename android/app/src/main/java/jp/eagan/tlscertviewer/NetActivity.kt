package jp.eagan.tlscertviewer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter

class NetActivity : AppCompatActivity() {
    class Protocol(val label:String, val port: Int)

    val PROTOCOL_LIST = arrayOf(
        Protocol("HTTPS", 443),
        Protocol("IMAPS", 993),
        Protocol("IMAP (STARTTLS)", 143),
        Protocol("SMTPS", 465),
        Protocol("SMTP (STARTTLS)", 25),
        Protocol("POP3S", 995)
    )
    val PROTOCOL_LABEL_LIST = PROTOCOL_LIST.map{ it.label }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_net)

        val protocol: Spinner = findViewById(R.id.net_protocol)
        val protocolAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            PROTOCOL_LABEL_LIST
        ).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        protocol.adapter = protocolAdapter
    }
}