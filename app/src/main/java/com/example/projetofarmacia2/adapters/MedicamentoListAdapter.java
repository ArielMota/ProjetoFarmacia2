package com.example.projetofarmacia2.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.projetofarmacia2.MainActivity;
import com.example.projetofarmacia2.R;
import com.example.projetofarmacia2.api_manipulacao.APIconfig;
import com.example.projetofarmacia2.fragments.FragmentMedicamentosDetalhes;
import com.example.projetofarmacia2.fragments.FragmentProdutosBaratos;
import com.example.projetofarmacia2.model.Medicamento;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MedicamentoListAdapter extends RecyclerView.Adapter<MedicamentoListAdapter.ListViewHolder> implements Filterable {

    private List<Medicamento> medicamentos;
    private List<Medicamento> listaCheiaMedicamentos;
    private Context context;
    Dialog mDialog;
    ViewPager mViewPager;
    Activity activity;

    //RequestOptions option;

    public MedicamentoListAdapter(List<Medicamento> medicamentos, Context context, Activity activity) {
        this.medicamentos = medicamentos;
        this.context = context;
        this.activity = activity;
        listaCheiaMedicamentos = new ArrayList<>(medicamentos);
        //option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        //context = viewGroup.getContext();
        LayoutInflater inflater =  LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_list_medicamento_gridlayout,viewGroup,false);
        final ListViewHolder listViewHolder = new ListViewHolder(view);






        return listViewHolder;
    }





    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int position) {

        final Medicamento medicamento = medicamentos.get(position);


        // mDialog = new Dialog(context,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
       // mDialog.setContentView(R.layout.dialog_produto);


        // Captura os clicks no mais informações
        listViewHolder.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                FragmentMedicamentosDetalhes fragment = new FragmentMedicamentosDetalhes();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("idImagem", (ArrayList<String>) medicamento.getLista_id_imagens());
                bundle.putString("nome",medicamento.getNome());
                bundle.putSerializable("medicamento",medicamento);

                fragment.setArguments(bundle);


                FragmentTransaction fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fragment_content, fragment);

                fragmentTransaction.addToBackStack(null);//Permite que retorne ao fragment anterior

                fragmentTransaction.commit();
            }
        });


        //click longo nos cardViews

        listViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                FragmentMedicamentosDetalhes fragment = new FragmentMedicamentosDetalhes();

                Bundle bundle = new Bundle();
                bundle.putStringArrayList("idImagem", (ArrayList<String>) medicamento.getLista_id_imagens());
                bundle.putString("nome",medicamento.getNome());
                bundle.putSerializable("medicamento",medicamento);

                fragment.setArguments(bundle);


                FragmentTransaction fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fragment_content, fragment);

                fragmentTransaction.addToBackStack(null);//Permite que retorne ao fragment anterior

                fragmentTransaction.commit();

                return false;
            }
        });


       //Captura os clicks nos cardViews
        listViewHolder.itemView.setOnClickListener(
               new View.OnClickListener() {
                   @Override
                   public void onClick(final View v) {



                       final Medicamento medicamento = medicamentos.get(position);




                       FragmentProdutosBaratos fragment = new FragmentProdutosBaratos();

                       Bundle bundle = new Bundle();
                       bundle.putString("medicamento", medicamento.getNome());
                       fragment.setArguments(bundle);

                       FragmentTransaction fragmentTransaction = ((MainActivity)context).getSupportFragmentManager().beginTransaction();

                       fragmentTransaction.replace(R.id.fragment_content, fragment);

                       fragmentTransaction.addToBackStack(null);//Permite que retorne ao fragment anterior

                       fragmentTransaction.commit();


;

                      // mDialog.show();


                       /*ImageView btn_exit = (ImageView) mDialog.findViewById(R.id.img_x);

                       btn_exit.setOnClickListener(new View.OnClickListener() {
                           @Override
                           public void onClick(View view) {
                               mDialog.dismiss();
                           }
                       });*/









                   }
               }
       );


        TextView nome_medicamento =  listViewHolder.nomeMedicamento;
        nome_medicamento.setText(String.valueOf(medicamento.getNome()));

        TextView preco_medicamento =  listViewHolder.precoMedicamento;
        preco_medicamento.setText("$ "+medicamento.getPreco());

        TextView principio_ativo_medicamento =  listViewHolder.principioAtivoMedicamento;
        principio_ativo_medicamento.setText(String.valueOf(medicamento.getPrincipioAtivo()));

        ImageView imageView = (ImageView) listViewHolder.imgMedicamento;
        if(!medicamento.getLista_id_imagens().isEmpty()){
            Picasso.get().load(APIconfig.URL+"/imagem/"+ medicamento.getLista_id_imagens().get(0)).resize(500,500)
                .centerCrop().into(imageView);}


        //ImageView imagem = listViewHolder.imgProduto;
        //Resources res = context.getResources();
        //imagem.setImageResource(medicamentos.get(position).getImg());
        //imagem.setImageResource(medicamentos.get(position).getImg());

        //Glide.with(context).load(medicamentos.get(position).getImg()).apply(option).into(listViewHolder.imgProduto);

        //Picasso.with(context).load(medicamentos.get(position).getImg()).into(listViewHolder.imgProduto);
    }





    public class ListViewHolder extends  RecyclerView.ViewHolder {

        public ImageView imgMedicamento;
        public TextView nomeMedicamento;
        public TextView precoMedicamento;
        public TextView principioAtivoMedicamento;
        public  ImageView info;

        private CardView item_produto;



        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            imgMedicamento = (ImageView) itemView.findViewById(R.id.img_medicamento);
            nomeMedicamento = (TextView) itemView.findViewById(R.id.nome_medicamento);
            precoMedicamento= (TextView) itemView.findViewById(R.id.preco_medicamento);
            principioAtivoMedicamento = (TextView) itemView.findViewById(R.id.pricipio_ativo);
            item_produto = (CardView) itemView.findViewById(R.id.produto_item_card);
            info = (ImageView) itemView.findViewById(R.id.info_medicamento);
        }
    }

    @Override
    public int getItemCount() {
        return medicamentos.size();
    }

    @Override
    public Filter getFilter() {

        return  produtoFiltro;
    }

    Filter produtoFiltro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Medicamento> listaFiltrada = new ArrayList<>();

            if (constraint == null || constraint.length() == 0){
                listaFiltrada.addAll(listaCheiaMedicamentos);
            }else {
                String filtroPadrao = constraint.toString().toLowerCase().trim();

                for (Medicamento medicamento : listaCheiaMedicamentos){
                    if (medicamento.getNome().toLowerCase().contains(filtroPadrao) ){
                        listaFiltrada.add(medicamento);
                    }else if (medicamento.getPrincipioAtivo().toLowerCase().contains(filtroPadrao) ) {
                        listaFiltrada.add(medicamento);
                    }
                }

            }

            FilterResults resultados = new FilterResults();

            resultados.values = listaFiltrada;

            return resultados;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            medicamentos.clear();
            medicamentos.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

}
