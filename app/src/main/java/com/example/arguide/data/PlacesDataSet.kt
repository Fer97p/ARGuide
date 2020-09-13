package com.example.arguide.data

import com.example.arguide.R
import com.example.arguide.ui.entities.Place
import com.google.android.gms.maps.model.LatLng

class PlacesDataSet {
    fun createDataSet(): List<Place> {
        return listOf(
            Place(
                "Antigua",
                "Iglesia de Santa María la Antigua",
                "La iglesia de Santa María de La Antigua se levanta desde al menos el siglo XII en la ciudad de Valladolid. Conserva de fines de esa centuria o " +
                        "principios de la siguiente una esbelta torre románica rematada con una flecha recubierta de teja, que la hace ser el campanario románico más alto de España en la actualidad, " +
                        "y un pórtico en el lado norte también románico. El resto del edificio es gótico y neogótico, pues se levantó en el siglo XIV y, tras posteriores añadidos en diferentes estilos, " +
                        "fue intensamente restaurado y reconstruido en la primera mitad del siglo XX.",
                R.drawable.antigua_slider2,
                LatLng(41.653490, -4.722820),
                0
            ), Place(
                "Angustias",
                "Iglesia de Nuestra Señora de las Angustias",
                "La Iglesia Penitencial de Nuestra Señora de las Angustias es una iglesia vallisoletana situada en el centro de la ciudad frente al Teatro Calderón " +
                        "y cerca de la Iglesia de Santa María La Antigua. La iglesia es propiedad y sede de la Ilustre Cofradía Penitencial de Nuestra Señora de las Angustias una de las cofradías más " +
                        "antiguas de la Semana Santa de Valladolid. La historia de esta iglesia está ligada a la de su cofradía, fundada con anterioridad a 1536, que se situó inicialmente en la calle " +
                        "Torrecilla, llamada en el siglo XVI calle de las Angustias Viejas, debido a que en los primeros años de aquel siglo era donde estaba situado el primitivo oratorio de esta cofradía. " +
                        "A finales del siglo XVI la cofradía se traslada a la iglesia actual, frente al antiguo Palacio del Almirante, sobre el que se levantó en el siglo XIX el Teatro Calderón.",
                R.drawable.angustias_slider1,
                LatLng(41.654110, -4.723860),
                0
            ), Place(
                "Teatro",
                "Teatro de la comedia",
                "El teatro de la Comedia fue un hoy desaparecido teatro español que se levantó originalmente en 1609-1612 en la ciudad de Valladolid (hoy comunidad " +
                        "autónoma de Castilla y León) sobre un anterior patio de comedias. Estaba situado en la plaza de la Comedia o plazuela del Teatro, llamada tiempo después plaza Martí y Monsó. " +
                        "Fue modificado varias veces conservando su disposición primitiva hasta principios del siglo XX, cuando fue remodelado y pasó a llamarse Gran Teatro. Sobrevivió luego como cine " +
                        "Coca (a partir de 1987), siendo derribado finalmente en 2003.",
                R.drawable.teatro,
                LatLng(41.652210, -4.730590),
                0
            ),
            Place(
                "Conde",
                "Monumento al conde Pedro Ansúrez",
                "El monumento al conde Pedro Ansúrez es una escultura pública conmemorativa en honor al conde Pedro Ansúrez, repoblador de Valladolid, ubicada en el centro de la plaza Mayor de " +
                        "la ciudad. Fue realizado en 1903 por el escultor vallisoletano Aurelio Rodríguez Vicente Carretero. El entorno del monumento ha variado significativamente con el tiempo desde su " +
                        "inauguración en 1903. Tres años más tarde fue colocada a su alrededor una verja de hierro que lo acotaba que fue sustituida más tarde por un jardín que fue finalmente suprimido.",
                R.drawable.conde,
                LatLng(41.651896, -4.728403),
                0
            ),
            Place(
                "FuenteDorada",
                "Plaza de Fuente Dorada",
                "Su trazado se remonta al siglo XIII, cuando en la zona también se proyectó la plaza del Mercado (hoy Plaza Mayor). El espacio sirvió para el intercambio comercial de bienes, de esta actividad es como se le conoció en 1603, La Gallinería Vieja, ya que se despachaban aves de corral ya muertas.\n" +
                        "Ya en el siglo XVII se le conoció como la plaza de la Fuente Dorada, por su fuente de bronce dorado allí instalada en 1616, obra de Diego de Praves, y que llevaba agua fresca por el viaje de Argales. Esta fuente fue reemplazada en más de una ocasión. En 1997 se realizó la remodelación que actualmente " +
                        "luce, la fuente de los oficios y las estaciones." +
                        "La zona, de trazado triangular e irregular, está rodeada de soportales que se conocieron por el nombre de los oficios que allí se ejercían. Así mismo luce en el centro una fuente monumental con esculturas de los principales oficios que se practicaban en la zona: alfareros, lavanderas, aguadoras, lanceros, plateros..." +
                        " Estas imágenes, distribuidas hacia los puntos cardinales, se intercalan con alegorías a las cuatro estaciones.",
                R.drawable.fuentedorada,
                LatLng(41.652160, -4.726350),
                0
            ),
            Place(
                "PlazaMayor",
                "Plaza Mayor de Valladolid",
                "La existencia de la plaza Mayor en el actual emplazamiento comenzó a definirse a mediados del siglo XIII cuando el mercado se desplazó desde la Plaza de Santa María a la Plaza del Mercado," +
                        " que desde comienzos del XVI se llamó Plaza Mayor. Tras su destrucción, a causa del grave incendio que comenzó el 21 de septiembre de 1561 y que hasta el 23 de septiembre destruyó al menos 440 casas " +
                        "de la ciudad, el concejo inició, al día siguiente de la finalización del incendio, el 24 de septiembre, las labores de" +
                        " reconstrucción. Se encargó a Francisco de Salamanca la construcción de tiendas provisionales en la propia plaza " +
                        "y se realojó a los afectados por el incendio, utilizando para ello casas prestadas por los propios vecinos. Las plazas mayores de Madrid y Salamanca, que datan de 1617 y 1729 respectivamente presentan un claro influjo procedente de la Plaza " +
                        "Mayor vallisoletana",
                R.drawable.plazamayor,
                LatLng(41.651699, -4.729135),
                0
            ),
            Place(
                "Roxy",
                "Sala de cine Roxy",
                "Fue construido en 1936 por el joven y notable arquitecto Ramón Pérez Lozana.\n" +
                        "Emplazado en la calle María de Molina, de planta rectangular sobre el que se elevan dos pisos. La arquitectura corresponde al tipo moderno. La fachada, de " +
                        "líneas sencillas, alteradas tan solo por una marquesina, buscando los efectos de la luz y decoración a base de grandes ventanales y artísticas vidrieras. La " +
                        "obra a excepción de la que por su naturaleza requería obreros especializados fue efectuada por artistas locales. La película que se proyectó en la inauguración" +
                        " fue “Don Quintín el amargao”.",
                R.drawable.roxy,
                LatLng(41.649590, -4.730500),
                0
            ),
            Place(
                "LopeVega",
                "Teatro Lope de Vega",
                "Se sitúa en la calle de María de Molina, en el centro de la ciudad. Diseñado por el arquitecto Jerónimo de la Gándara, fue inaugurado en 1861. " +
                        "En el siglo XVII en el solar que ocupa actualmente el teatro se encontraba el Teatro de la Comedia del Hospital de San José de la ciudad. Dicho teatro se" +
                        " mantuvo en activo hasta mediados del siglo XIX, cuando sus deficiencias estructurales y peligro de ruina alimentaron la idea de que el Teatro de la Comedia " +
                        "debía ser reemplazado por uno nuevo. Cerró de manera permanente el 30 de abril de 2000. Tras ser adquirido por Caja Duero en 2006, sigue a la espera de rehabilitación.",
                R.drawable.lopevega,
                LatLng(41.650040, -4.731210),
                0
            ),
            Place(
                "SanLorenzo",
                "Iglesia de San Lorenzo",
                "Fue mandada construir en el siglo XV por el conde Pedro Niño. La iglesia original gótica de tres naves, sufrió en la década de 1970 el " +
                        "derribo total de lo que se conservó la torre de la capilla mayor, decorada con bolas, pináculos y gárgolas y la portada del tempo. El edificio " +
                        "fue convertido en viviendas en cuya planta baja se construyó un nuevo templo, conservándose tan sólo la portada y la torre cuadrada, ahogada entre " +
                        "una arquitectura supuestamente vanguardista. Su rico patrimonio fue diseminado por el contiguo convento de Santa Ana y en la propia iglesia, en cuyo " +
                        "altar recibe culto la patrona de Valladolid..",
                R.drawable.sanlorenzo,
                LatLng(41.651470, -4.731900),
                0
            ),
            Place(
                "Ayuntamiento",
                "Casa consistorial de Valladolid",
                " A raíz del incendio de 1561 y la destrucción total del entorno, el Ayuntamiento decidió cambiar la ubicación del consistorio, " +
                        "ocupando el lugar que tiene actualmente y presidiendo la reformada Plaza Mayor. Las obras se iniciaron en 1562 y terminaron en 1577. En el siglo XVII " +
                        "se construyeron los torreones laterales y en 1833 se erigió la torre del reloj. La antigua casa Consistorial constaba de dos cuerpos; el " +
                        "inferior poseía tres balcones a cada lado y una puerta central y el cuerpo superior tenía una serie de 17 arcos sucesivos." +
                        "Este edificio se encontraba al llegar el siglo XIX en un lamentable estado de conservación, por lo que en 1879 a propuesta del alcalde " +
                        "de la ciudad Miguel Íscar, el pleno municipal del 10 de febrero acordó el derribo de este viejo consistorio para la construcción de otro más moderno.",
                R.drawable.ayuntamiento,
                LatLng(41.651587, -4.729154),
                0
            ),
            Place(
                "Catedral",
                "Catedral de Nuestra Señora de la Asunción",
                "Concebida en el siglo XVI y diseñada por el arquitecto Juan de Herrera, es un edificio de estilo herreriano con añadidos barrocos. Entre 1703 y " +
                        "1709 se levantó la torre del lado del Evangelio. Años más tarde, se le añadió un piso más, ochavado " +
                        "con huecos donde se albergaron las campanas, en un número mayor de lo previsto y rematado con cúpula de cascos y linterna. La torre empezó a dar " +
                        "problemas, y a lo largo del siglo XVIII se hicieron tres reparaciones, hasta que en el siglo siguiente, en 1841, se desmoronó toda la parte de arriba," +
                        " arrastrando gran parte del tercer y segundo cuerpo.",
                R.drawable.catedral,
                LatLng(41.652370, -4.723750),
                0
            ),
            Place(
                "Canovas",
                "Calle de Cánovas del Castillo",
                "Esta céntrica calle, que une la Plaza de Fuente Dorada con la calle Regalado, fue llamada en un principio Lorigueros, ya que en ella al " +
                        "parecer se vendían lorigas, y también fue conocida como La Frenería por venderse frenos. Hacia 1800 se la denominaba calle Orates, nombre que " +
                        "mantuvo hasta 1897 que se cambió al nombre actual en honor al literato, historiador y Jefe de Gobierno D. Antonio Cánovas del Castillo, asesinado " +
                        "el 8 de agosto de 1897 en la Balneario de Santa Agueda en Guipúzcoa.",
                R.drawable.canovas,
                LatLng(41.651996, -4.724531),
                0
            ),
            Place(
                "PzaUni",
                "Universidad de Valladolid",
                "La fachada barroca de la Universidad de Valladolid fue construida entre 1716-1718, bajo la dirección de los Padres del Convento " +
                        "del Carmen Descalzo, de Valladolid, siguiendo las trazas de Fray Pedro de la Visitación y que fue debida a la ampliación y reformas llevadas a " +
                        "cabo en el edificio de la Universidad al haberse quedado con poco espacio para sus necesidades. " +
                        "En ella se encuentran distintos grupos escultóricos de calidad y que representan alegorías de las materias que se impartían en el edificio." +
                        " En la balaustrada se disponen cuatro esculturas que representan a los reyes que favorecieron a la Universidad vallisoletana.",
                R.drawable.pzauni,
                LatLng(41.652994, -4.721533),
                0
            ),
            Place(
                "TeatroCalderon",
                "Teatro Calderón",
                "El Teatro Calderón es el principal teatro de la ciudad española de Valladolid. Este teatro recibe el nombre de Calderón de la Barca, " +
                        "importante poeta y dramaturgo español. Está situado en el centro de la ciudad a pocos metros de otros monumentos significativos como la Catedral, " +
                        "la iglesia Penitencial de Nuestra Señora de las Angustias o la iglesia de Santa María La Antigua. Cada año es la sede de la Semana Internacional de " +
                        "Cine de Valladolid (SEMINCI). En el espacio en el que hoy se encuentra el teatro, se levantó hasta mediados del siglo XIX el palacio del Almirante de Castilla, " +
                        "gran edificio de origen bajomedieval del que se tiene escasa información y que fue totalmente demolido para edificar el actual teatro.",
                R.drawable.teatrocalderon,
                LatLng(41.653591, -4.724424),
                0
            ),
            Place(
                "Fabrica",
                "Fábrica de harinas La Perla",
                "La fábrica de harinas «La Perla» fue una antigua harinera vallisoletana construida entre 1856 y 1857 que funcionaba gracias a la energía hidráulica " +
                        "proporcionada por el Canal de Castilla. El edificio fue declarado Bien de Interés Cultural en 1991 y la fábrica cerró en 2006." +
                        "Desde 2008 albergó el Hotel Marqués de la Ensenada, un alojamiento de lujo que cerró de forma polémica por cuestiones económicas en 2016. Dos años después " +
                        "el edificio fue okupado y se inauguró el proyecto Centro Social La Molinera para la reutilización autogestionada del inmueble.",
                R.drawable.fabrica,
                LatLng(41.660333, -4.733780),
                0
            ),
            Place(
                "Biblioteca",
                "Biblioteca de Castilla y León",
                "Esta biblioteca se ubica en el palacio de los Condes de Benavente, en la plaza de la Trinidad de Valladolid. Su construcción comienza en 1515. " +
                        "Fue sede de las Cortes Generales y de los reales consejos. Con Juan Francisco Alonso Pimentel alojó la mejor colección privada de arte que ha existido " +
                        "en Valladolid. Sufrió graves incendios en 1667 y 1716. En 1847 lo adquirió la Diputación de Valladolid como Hospicio hasta la década de los años 1970. " +
                        "Se convirtió en la actual Biblioteca desde el año 1990.",
                R.drawable.biblioteca,
                LatLng(41.657367, -4.729945),
                0
            ),
            Place(
                "SanAgustin",
                "Iglesia de San Agustín",
                "La Iglesia de San Agustín de Valladolid, es hoy la sede del Archivo Histórico Municipal de Valladolid. Su construcción se prolongó entre los años 1550 y 1627." +
                        " En 1801, con el estallido de la Guerra de Independencia y la invasión napoleónica el edificio sirvió de albergue para las tropas francesas " +
                        " que se hospedaron en Valladolid, lo que ocasionó importantes deterioros y el expolio por parte de los franceses de las riquezas del convento. " +
                        "En 1814 se retoma la vida conventual con tan solo cuatro monjes. Desde principios del siglo XX, la iglesia se mantuvo en un estado de ruina, hasta que se rehabilitó en el año 2002.",
                R.drawable.sanagustin,
                LatLng(41.655014, -4.730621),
                0
            ),
            Place(
                "MercadoVal",
                "Mercado del Val",
                "El mercado del Val es un mercado en Valladolid (España). Es un ejemplo de arquitectura del hierro. Se alza en la plaza del Val, junto a " +
                        "la iglesia de San Benito el Real. Se trata del mercado más antiguo que se conserva en la ciudad, tras el derribo de los de El Campillo y Portugalete, " +
                        "los tres construidos durante la alcaldía de Miguel Íscar Juárez. Está inspirado en Les Halles de París, y fue construido entre los años 1878 y 1882, " +
                        "con proyecto del arquitecto Joaquín Ruiz Sierra.",
                R.drawable.mercadoval,
                LatLng(41.654048, -4.729348),
                0
            ),
            Place(
                "MuseoNacional",
                "Museo Nacional de Escultura",
                "El Colegio de San Gregorio de Valladolid es la sede principal del Museo Nacional de Escultura. Las obras se iniciaron en 1488 aunque " +
                        "se había comenzado ya la construcción de la capilla funeraria, cuya puerta de entrada se percibe en el crucero sur de la Iglesia conventual de San Pablo. " +
                        "El edificio se supone finalizado en 1496. En el siglo XIX cesó su actividad como colegio, para pasar desde el 29 de abril de 1933 a " +
                        "convertirse en sede del Museo Nacional de Escultura. Tras la remodelación efectuada a comienzos del siglo XXI, desde septiembre de 2009 es " +
                        "la sede principal del Museo Nacional Colegio de San Gregorio, y acoge de nuevo la colección ",
                R.drawable.museonacional,
                LatLng(41.657760, -4.723380),
                0
            ),
            Place(
                "SanPablo",
                "Iglesia de San Pablo",
                "La iglesia conventual de San Pablo es uno de los edificios más representativos de la ciudad de Valladolid (Castilla y León, España) y " +
                        "atracción turística. Se encuentra en la plaza de San Pablo, lugar donde se hallan también el palacio Real y el palacio de Pimentel, los llamados " +
                        "sitios reales en siglos pasados. Se encuentra adosada al colegio de San Gregorio y próxima al resto de sedes del Museo Nacional de Escultura. " +
                        "Fue construida entre 1445 y 1616 y pertenece a la orden de los dominicos. En 1968 la iglesia sufrió un incendio y en los años siguientes se llevaron" +
                        " a cabo varias restauraciones.",
                R.drawable.sanpablo,
                LatLng(41.656778, -4.725124),
                0
            ),
            Place(
                "Santiago",
                "Iglesia de Santiago Apóstol",
                "La iglesia se construyó sobre una pequeña ermita de comienzos del siglo XII, que hacia 1400 sería elevada a la categoría de " +
                        "iglesia parroquial. Como consecuencia del desarrollo de la zona del centro urbano de Valladolid, motivada por la actividad comercial " +
                        "de la cercana Plaza Mayor, a finales del siglo XV la iglesia se había quedado pequeña para acoger a la creciente feligresía. Además, la cabecera " +
                        "presentaba un estado ruinoso. El rico mercader y banquero D. Luis de la Serna se convirtió en el patrono del templo, acordando con la autoridad " +
                        "eclesiástica costear su completa reedificación a cambio de permitírsele utilizar la Capilla Mayor como panteón familiar.",
                R.drawable.santiago,
                LatLng(41.650938, -4.728529),
                0
            )
        )
    }
}