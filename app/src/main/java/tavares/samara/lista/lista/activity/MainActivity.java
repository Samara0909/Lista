package tavares.samara.lista.lista.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import tavares.samara.lista.R;
import tavares.samara.lista.lista.adapter.MyAdapter;
import tavares.samara.lista.lista.model.MyItem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import tavares.samara.lista.R;
import tavares.samara.lista.lista.adapter.MyAdapter;
import tavares.samara.lista.lista.model.MyItem;

public class MainActivity extends AppCompatActivity {
    static int NEW_ITEM_REQUEST =1; //identifica a requisição de novo item
    List<MyItem> itens = new ArrayList<>(); // lista de itens- MyItem

    MyAdapter myAdapter; // adapter para o RecyclerView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this); // habilita a extensão de bordas
        setContentView(R.layout.activity_main); // define o layout da atividade
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem); // obtem referência ao FAB
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class); // cria intent para NewItemActivity
                startActivityForResult(i, NEW_ITEM_REQUEST); // inicia a atividade esperando um resultado
            }
        });

        RecyclerView rvItens = findViewById(R.id.rvItens); // obtém referencia ao RecyclerView
        myAdapter = new MyAdapter(this,itens); // inicializa o adapter
        rvItens.setAdapter(myAdapter); // define o adapter no RecyclerView
        rvItens.setHasFixedSize(true); // otimiza o RecyclerView sabendo que o tamanho não mudará
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // cria um LayoutManager linear
        rvItens.setLayoutManager(layoutManager); // define o LayoutManager no RecyclerView
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL); // cria uma decoração de divisores
        rvItens.addItemDecoration(dividerItemDecoration); // adiciona a decoração ao RecyclerView
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) { //confere se a requisiçao é de novo item
            if (resultCode == Activity.RESULT_OK) { // analisa se o resultado é OK
                MyItem myItem = new MyItem(); // cria um novo item
                //define o titilo, descriçao e foto do item e adiciona ele a lista
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();
                itens.add(myItem);
                myAdapter.notifyItemInserted(itens.size()-1); // notifica o adapter que um novo item foi inserido
            }
        }
    }
}
