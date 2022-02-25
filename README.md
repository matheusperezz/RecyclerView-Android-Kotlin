# RecyclerView-Android-Kotlin

[![Kotlin](https://img.shields.io/badge/kotlin-1.6.0-blue.svg?logo=kotlin)](http://kotlinlang.org)

Este é um breve tutorial passo a passo de como fazer um recyclerView com o CardView e interação com cliques no Android Studio com Kotlin.

## O que é o RecyclerView

Segundo o próprio site de desenvolvedor do android: 

O RecyclerView facilita e torna eficiente a exibição de grandes conjuntos de dados. Você fornece os dados e define a aparência de cada item, e a biblioteca RecyclerView, quando necessário, cria os elementos dinamicamente.

O RecyclerView, como o nome indica, recicla esses elementos individuais. Quando um item rola para fora da tela, o RecyclerView não destrói a visualização dele. Em vez disso, o RecyclerView reutiliza a visualização para novos itens que passaram a aparecer na tela. Isso melhora muito o desempenho, aperfeiçoando a capacidade de resposta do app e reduzindo o consumo de energia.

## Adicionando Dependências manualmente

Primeiramente vamos colocar as dependências necessárias dentro do `build.gradle` no escopo do aplicativo.

```
plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'

    // Adicionado para suporte ao binding
    id 'kotlin-android-extensions'
}

...

dependencies {

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'

    // Card View
    implementation "androidx.cardview:cardview:1.0.0"

    // RecyclerView
    implementation "androidx.recyclerview:recyclerview:1.2.1"

    // Glide
    def glide_version = "4.12.0"
    implementation "com.github.bumptech.glide:glide:$glide_version"
    annotationProcessor "com.github.bumptech.glide:compiler:$glide_version"
}

```
### Aqui Adicionamos:
* **CardView** - Que nada mais é que um modelo de exibição para o RecyclerView.
* **RecyclerView** - Implementando o RecyclerView em si.
* **Glide** - É uma biblioteca para carregarmos as imagens que serão usadas nesse exemplo.
* **kotlin-android-extensions** - Um meio para facilitar o uso dos componentes de Interface no código.

## Modelagem de Dados do CardView

Nessa parte iremos configurar que tipo de dados serão utilizados nos Cards.

1º - Dentro do pacote onde se encontra a MainActivity, Crie um pacote com o nome `models` e dentro deste pacote crie uma DataClass que carregará os seus dados do CardView como na imagem abaixo, eu utilizei o nome "CardCarro" pois farei uma lista de demonstração com alguns carros, mas, você pode colocar o nome que for mais sugestivo a sua proposta.
Para fins didáticos estou misturando alguns idiomas mas não é aconselhável misturar linguas dentro do código.

<img src="https://i.ibb.co/J24cjcp/dataclass.png" alt="dataclass" border="0">

2º - Dentro da sua classe modelo, insira os dados que seu Card irá carregar

```kotlin
package com.example.recyclerviewcarros.models

data class CardCarro(
    var modelo : String,
    var marca : String,
    var carImageUrl : String,
    var link : String,
)

```

## Criando base de dados

Para demonstração estarei salvando os dados dentro do próprio código mas não é aconselhavel tomar essa atitude em produção!

Criaremos uma classe no pacote principal da aplicação onde se encontra a MainActivity com o nome `DataSource` ela carregará uma lista de exemplos para os CardViews que serão utilizados para popular nosso RecyclerView. 
Com isso em mente iremos criar um método que irá criar uma lista de 'CardCarro' como base de dados.

```kotlin
class DataSource {

    companion object {

        fun createDataSet() : ArrayList<CardCarro>{

            var list = ArrayList<CardCarro>()

            list.add(
                CardCarro(
                    "812 GTS",
                    "Ferrari",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/Ferrari_812_Superfast_IMG_0798.jpg/800px-Ferrari_812_Superfast_IMG_0798.jpg?20170917223834",
                    "https://www.ferrari.com/en-BR/auto/812-gts"
                )
            )

            list.add(
                CardCarro(
                    "911 Turbo S",
                    "Porsche",
                    "https://s2.glbimg.com/A27_aeYOMcY_jY5lTDRUefbgdS4=/984x0/smart/filters:strip_icc()/i.s3.glbimg.com/v1/AUTH_63b422c2caee4269b8b34177e8876b93/internal_photos/bs/2021/0/a/ne1oJrTyeyqc9IVnYBKg/foto28emp-101-posrche-b10.jpg",
                    "https://www.porsche.com/brazil/pt/models/911/911-turbo-models/911-turbo-s/"
                )
            )

            list.add(
                CardCarro(
                    "Artura",
                    "McLaren",
                    "https://img.olhardigital.com.br/wp-content/uploads/2021/02/Large-12920-McLarenArtura-1024x657.jpg",
                    "https://cars.mclaren.com/br-pt/artura"
                )
            )

            list.add(
                CardCarro(
                    "Huracan Performante",
                    "Lamborghini",
                    "https://allthecars.files.wordpress.com/2018/03/lamborghini-huracan-performante-spyder-1.jpg",
                    "https://www.lamborghini.com/en-en/brand/masterpieces/huracan-performante"
                )
            )

            list.add(
                CardCarro(
                    "900 Rocket",
                    "Brabus",
                    "https://www.brabus.com/_Resources/Persistent/0/a/3/6/0a362948e2bbc4f3462cc7724d684c087dbfc6b5/001-2560x1813.jpg?bust=0a362948",
                    "https://www.brabus.com/en/cars/cars-for-sale/C4S140.html"
                )
            )

            return list

        }

    }

}
```

## Criando Layout do CardView

Dentro da pasta `res` vamos em `layout` e criar um `Layout Resource file` que será o layout do nosso CardView onde usaremos todos os componentes da classe 'CardCarro'

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <androidx.cardview.widget.CardView
        app:cardUseCompatPadding="true"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:cardCornerRadius="2dp"
        app:cardElevation="8dp"
        app:cardPreventCornerOverlap="false"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent">

        <androidx.constraintlayout.widget.ConstraintLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content">

            <ImageView
                android:id="@+id/imagemCarro"
                android:layout_width="match_parent"
                android:layout_height="0dp"
                android:layout_margin="0dp"
                android:adjustViewBounds="true"
                android:padding="0dp"
                android:scaleType="fitXY"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintTop_toTopOf="parent"
                app:layout_constraintLeft_toLeftOf="parent" />

            <LinearLayout
                android:id="@+id/container1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:orientation="vertical"
                android:padding="10dp"
                app:layout_constraintBottom_toBottomOf="parent"
                app:layout_constraintRight_toRightOf="parent"
                app:layout_constraintLeft_toLeftOf="parent"
                app:layout_constraintTop_toBottomOf="@id/imagemCarro">

                <TextView
                    android:id="@+id/modelo"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:text="Modelo do carro"
                    android:textColor="#000"
                    android:textSize="19sp" />

                <TextView
                    android:id="@+id/fabricante"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    android:layout_marginTop="10dp"
                    android:text="Fabricante"
                    android:textSize="15sp" />

            </LinearLayout>

        </androidx.constraintlayout.widget.ConstraintLayout>

    </androidx.cardview.widget.CardView>

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Criando o Adapter

O que seria necessariamente um Adapter? Segundo o próprio site de desenvolvedor do android: 

Um objeto Adapter atua como uma ponte entre um AdapterView e os dados subjacentes dessa exibição. O Adapter fornece acesso aos itens de dados. O Adapter também é responsável por fazer uma View para cada item do conjunto de dados.

Ele que irá carregar os CardViews e renderizar nossa lista. Para isso basta criar uma classe Kotlin dentro do pacote onde se encontra sua MainActivity com um nome referente a adapter 

```kotlin
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
```

## Adicionando RecyclerView dentro do layout da MainActivity

Dentro do arquivo `activity_main.xml` coloque o código:

```xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recyclerview"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
</androidx.constraintlayout.widget.ConstraintLayout>
```

também será introduzido no `androidManifest.xml` permissões para fazermos pesquisa na Internet, para consultar as imagens.

```xml
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.recyclerviewcarros">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.RecyclerViewCarros">
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

    <uses-permission android:name="android.permission.INTERNET"/>

</manifest>
```

## Adicionando Evento de Clique

Se todos os passos anteriormente foram seguidos de forma correta, será necessário somente manipular a MainActivity.kt 

```kotlin
class MainActivity : AppCompatActivity() {

    private lateinit var carroAdapter: CarroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        addDataSource()

    }

    private fun addDataSource() {

        val dataSource = DataSource.createDataSet()
        this.carroAdapter.setDataset(dataSource)
    }

    private fun initRecyclerView() {

        this.carroAdapter = CarroAdapter { live ->
            openLink(live.link)
        }

        // Disposição dos items no recycelrView que no caso aqui é Linear
        recyclerview.apply {
            recyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerview.adapter = carroAdapter
        }
    }

    private fun openLink(url : String) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)

    }
}
```
## Conclusão

Se todos os passos foram seguidos corretamente o aplicativo funcionará perfeitamente e se parecerá com algo assim:

<img src="https://i.ibb.co/RbV0nC9/Screenshot-20220225-172708.png" alt="Screenshot-20220225-172708" border="0">

