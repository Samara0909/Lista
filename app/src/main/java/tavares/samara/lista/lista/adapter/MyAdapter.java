package tavares.samara.lista.lista.adapter;

import androidx.annotation.NonNull;

import tavares.samara.lista.lista.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter {
    MainActivity mainActivity;
    List<MyItem> itens;
    public MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView;

        ImageView imvfoto = v.findViewById(R.id.imvfoto);
        imvfoto.setImageURI(myItem.foto);

        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);

        TextView tvdesc = v.findViewById(R.id.tvdesc);
        tvdesc.setText(myItem.description);
    }
    @Override
    public int getItemCount() {return itens.size();}
    public class MyViewHolder extends RecyclerView.ViewHolder {
          public MyViewHolder(@NonNull View itemView) {
              super(itemView);   }
      }

    }

