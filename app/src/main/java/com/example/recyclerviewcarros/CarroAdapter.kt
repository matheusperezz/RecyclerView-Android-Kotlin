package com.example.recyclerviewcarros

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.recyclerviewcarros.models.CardCarro
import kotlinx.android.synthetic.main.res_main_carro.view.*

// Adicionando onItemClicked para usarmos evento de click
class CarroAdapter(private val onItemClicked : (CardCarro) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var items : List<CardCarro> = ArrayList()

    // Método que é chamado para criar a View Holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LiveViewHolder(
            // Quem é o Layout que vamos usar
            LayoutInflater.from(parent.context).inflate(R.layout.res_main_carro, parent, false)
        )

    }

    // Método que popula nossas informações
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        // Caso tenhamos outros tipos de Views
        when (holder) {
            is LiveViewHolder ->{
                holder.bind(items[position], onItemClicked)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setDataset(lives : List<CardCarro>){
        this.items = lives
    }

    // ViewHolder é uma implementação do nosso CardView no código
    class LiveViewHolder constructor(
        itemView : View
    ): RecyclerView.ViewHolder(itemView){

        private var carroModelo = itemView.modelo
        private var carroFabricante = itemView.fabricante
        private var carroImagem = itemView.imagemCarro


        // Pega os items da Model e coloca no recyclerView
        // Recebe onItemClicked da class
        fun bind(live: CardCarro, onItemClicked: (CardCarro) -> Unit) {

            carroModelo.text = live.modelo
            carroFabricante.text = live.marca

            // placeholder -> qual imagem irá mostra enquanto estiver carregando
            // error -> qual imagem será mostrada quando der erro
            val resquestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            // load -> qual imagem que ele vai carregar
            // into -> que imagem quer que ele carregue a imagem
            Glide.with(itemView.context)
                .applyDefaultRequestOptions(resquestOptions)
                .load(live.carImageUrl)
                .into(carroImagem)

            itemView.setOnClickListener{
                onItemClicked(live)
            }

        }

    }

}