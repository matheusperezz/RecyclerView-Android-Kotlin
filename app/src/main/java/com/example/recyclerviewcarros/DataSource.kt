package com.example.recyclerviewcarros

import com.example.recyclerviewcarros.models.CardCarro

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