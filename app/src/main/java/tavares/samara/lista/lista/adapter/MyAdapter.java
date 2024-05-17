package tavares.samara.lista.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tavares.samara.lista.R;
import tavares.samara.lista.lista.activity.MainActivity;
import tavares.samara.lista.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens; //itens a serem exibidos

    // construtor que inicializa o adapter com a MainActivity e a lista de itens
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    // método para criar novas visualizações (pelo LayoutManager)
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity); // obtem um LayoutInflater da MainActivity
        View v = inflater.inflate(R.layout.item_list, parent, false);
        return new MyViewHolder(v);
    }

    // metodo para substituir o conteudo de uma visualizaçao (chamado pelo LayoutManager)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position); // Obtém o item da lista
        View v = holder.itemView; // Obtém a view do item

        // Configura a imagem do item, o título do item e a descriçao do item
        ImageView imvfoto = v.findViewById(R.id.imvPhoto);
        imvfoto.setImageURI(myItem.photo);


        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);


        TextView tvdesc = v.findViewById(R.id.tvDesc);
        tvdesc.setText(myItem.description);
    }

    // metodo que retorna o tamanho da lista de itens (chamado pelo LayoutManager)
    @Override
    public int getItemCount() {
        return itens.size();
    }

    // Classe ViewHolder que armazena as views do item
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView); // Chama o construtor da superclasse com a view do item
        }
    }
}
