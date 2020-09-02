package com.example.arguide.ui.details

import android.graphics.text.LineBreaker.JUSTIFICATION_MODE_INTER_WORD
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel

import com.example.arguide.R
import com.example.arguide.ui.main.MainActivity

class DetailsFragment : Fragment() {

    private var areImagesAdded = false
    private lateinit var button: Button
    private val args: DetailsFragmentArgs by navArgs()
    private var sliderItems = ArrayList<SlideModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (!areImagesAdded) {
            addContent(args.place, args.placeName)
        }
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        button = requireView().findViewById(R.id.trip)
        button.setOnClickListener {
            val action =
                DetailsFragmentDirections.actionDetailsFragmentToMapsFragment(
                    args.place,
                    args.placeLat,
                    args.placeLong,
                    args.placeName
                )
            findNavController().navigate(action)
        }
        (activity as MainActivity).supportActionBar?.title = args.placeName
        val imageSlider = requireView().findViewById<ImageSlider>(R.id.image_slider)
        imageSlider.setImageList(sliderItems, ScaleTypes.CENTER_CROP)
        imageSlider.stopSliding()
        val textContainer: TextView = requireView().findViewById(R.id.textInfo)
        textContainer.text = args.placeDescription
        textContainer.movementMethod = ScrollingMovementMethod()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textContainer.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }

    }

    private fun addContent(place: String, name: String) {
        (activity as MainActivity).supportActionBar?.title = name
        if (place == "Antigua") {
            sliderItems.add(SlideModel(R.drawable.antigua_slider2, "Vista exterior de la iglesia"))
            sliderItems.add(SlideModel(R.drawable.antigua_slider1, "Interior de la iglesia"))
            sliderItems.add(
                SlideModel(
                    R.drawable.antigua_slider3,
                    "Imagen antigua antes de la restauración"
                )
            )
        }
        if (args.place == "Angustias") {
            sliderItems.add(
                SlideModel(
                    R.drawable.angustias_slider1,
                    "Vista de la fachada de la iglesia"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.angustias_slider2,
                    "Imagen de la iglesia de principios del siglo XX"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.angustias_slider3,
                    "Interior de la iglesia durante Semana Santa"
                )
            )
        }
        if (args.place == "Teatro") {
            sliderItems.add(
                SlideModel(
                    R.drawable.teatro_slider1,
                    "Primitivo teatro construido en 1609"
                )
            )
            sliderItems.add(SlideModel(R.drawable.teatro_slider2, "Fachada del teatro en 1907"))
            sliderItems.add(
                SlideModel(
                    R.drawable.teatro_slider3,
                    "Actual edificio de viviendas levantado en el solar"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.teatro_slider4,
                    "Boceto de la fachada que no llegó a realizarse"
                )
            )
        }
        if (place == "Ayuntamiento") {
            sliderItems.add(
                SlideModel(
                    R.drawable.ayuntamiento_slider1,
                    "Antigua fachada del edificio"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.ayuntamiento_slider2,
                    "Fachada del edificio tras la reforma"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.ayuntamiento_slider3,
                    "Imagen actual del edificio"
                )
            )
        }
        if (place == "Biblioteca") {
            sliderItems.add(
                SlideModel(
                    R.drawable.biblioteca_slider1,
                    "Imagen actual de la entrada a la biblioteca"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.biblioteca_slider2,
                    "Imagen de la entrada a la residencia provincial"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.biblioteca_slider3,
                    "Imagen antigua de la fachada del hospicio"
                )
            )
        }
        if (place == "Canovas") {
            sliderItems.add(
                SlideModel(
                    R.drawable.canovas_slider1,
                    "Imagen del antiguo edificio entre la calle Cánovas y Regalado"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.canovas_slider2,
                    "Comparación entre el edificio antiguo y el actual"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.canovas_slider3,
                    "Vista de las calles que rodean al edificio"
                )
            )
        }
        if (place == "Catedral") {
            sliderItems.add(
                SlideModel(
                    R.drawable.catedral_slider1,
                    "Dibujo que representa la catedral en la antiguedad"
                )
            )
            sliderItems.add(SlideModel(R.drawable.catedral_slider2, "Torre del Evangelio derruida"))
            sliderItems.add(
                SlideModel(
                    R.drawable.catedral_slider3,
                    "Antigua fachada de la catedral"
                )
            )
            sliderItems.add(SlideModel(R.drawable.catedral_slider4, "Interior de la catedral"))
        }
        if (place == "Conde") {
            sliderItems.add(SlideModel(R.drawable.conde_slider1, "Estatua en la actualidad"))
            sliderItems.add(
                SlideModel(
                    R.drawable.conde_slider2,
                    "Imagen antigua en la que se ve la verja que rodea la estatua"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.conde_slider3,
                    "Jardín que rodeaba la estatua del conde"
                )
            )
        }
        if (place == "Fabrica") {
            sliderItems.add(
                SlideModel(
                    R.drawable.fabrica_slider1,
                    "Imagen del edificio antes de convertirse en hotel"
                )
            )
            sliderItems.add(SlideModel(R.drawable.fabrica_slider2, "Imagen antigua del edificio"))
            sliderItems.add(SlideModel(R.drawable.fabrica_slider3, "Imagen actual del hotel"))
        }
        if (place == "FuenteDorada") {
            sliderItems.add(
                SlideModel(
                    R.drawable.fuentedorada_slider1,
                    "Aspecto de la fuente antes de su remodelación"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.fuentedorada_slider2,
                    "Fotografía antigua de la plaza"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.fuentedorada_slider3,
                    "Imagen de la fuente en 1857"
                )
            )
        }
        if (place == "LopeVega") {
            sliderItems.add(SlideModel(R.drawable.lopevega_slider1, "Interior del teatro"))
            sliderItems.add(SlideModel(R.drawable.lopevega_slider2, "Fachada antigua del teatro"))
            sliderItems.add(SlideModel(R.drawable.lopevega_slider3, "Fachada antigua del teatro"))
        }
        if (place == "PlazaMayor") {
            sliderItems.add(SlideModel(R.drawable.plazamayor_slider1, "Imagen antigua de la plaza"))
            sliderItems.add(SlideModel(R.drawable.plazamayor_slider2, "Imagen antigua de la plaza"))
            sliderItems.add(
                SlideModel(
                    R.drawable.plazamayor_slider3,
                    "Fotografía de la plaza en la actualidad"
                )
            )
        }
        if (place == "PzaUni") {
            sliderItems.add(
                SlideModel(
                    R.drawable.pzauni_slider1,
                    "Imagen antigua de la Universidad de Derecho"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.pzauni_slider2,
                    "Aspecto actual de la torre del reloj"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.pzauni_slider3,
                    "Fotografía de la Universidad de Derecho en la actualidad"
                )
            )
        }
        if (place == "Roxy") {
            sliderItems.add(SlideModel(R.drawable.roxy_slider1, "Antigua fachada del cine"))
            sliderItems.add(SlideModel(R.drawable.roxy_slider2, "Interior del edificio"))
            sliderItems.add(
                SlideModel(
                    R.drawable.roxy_slider3,
                    "Proyector R.C.A Photopone utilizado"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.roxy_slider4,
                    "Cartel de la película proyectada en la inauguración"
                )
            )

        }
        if (place == "SanAgustin") {
            sliderItems.add(
                SlideModel(
                    R.drawable.sanagustin_slider1,
                    "Aspecto del edificio a mediados del siglo XX"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.sanagustin_slider2,
                    "Interior del edificio entre el siglo XX y la actualidad"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.sanagustin_slider3,
                    "Vista aérea de las catas arqueológicas realizadas en los alrededores"
                )
            )
        }
        if (place == "SanLorenzo") {
            sliderItems.add(SlideModel(R.drawable.sanlorenzo_slider1, "Interior de la iglesia"))
            sliderItems.add(
                SlideModel(
                    R.drawable.sanlorenzo_slider2,
                    "Imagen antigua de la iglesia"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.sanlorenzo_slider3,
                    "Fachada de la iglesia en el siglo XX"
                )
            )
        }
        if (place == "TeatroCalderon") {
            sliderItems.add(SlideModel(R.drawable.teatrocalderon_slider1, "Interior del teatro"))
            sliderItems.add(
                SlideModel(
                    R.drawable.teatrocalderon_slider2,
                    "Imagen antigua de la fachada del teatro"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.teatrocalderon_slider3,
                    "Imagen del teatro en la actualidad"
                )
            )
        }
        if (place == "MercadoVal") {
            sliderItems.add(SlideModel(R.drawable.mercadoval_slider1, "Interior del mercado"))
            sliderItems.add(
                SlideModel(
                    R.drawable.mercadoval_slider2,
                    "Imagen antigua del mercado"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.mercadoval_slider3,
                    "Imagen del mercado reformado en la actualidad"
                )
            )
        }
        if (place == "MuseoNacional") {
            sliderItems.add(SlideModel(R.drawable.museonacional_slider1, "Fachada del museo en la actualidad"))
            sliderItems.add(
                SlideModel(
                    R.drawable.museonacional_slider2,
                    "Imagen antigua de la fachada del museo"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.museonacional_slider3,
                    "Imagen del patio interior"
                )
            )
        }
        if (place == "SanPablo") {
            sliderItems.add(SlideModel(R.drawable.sanpablo_slider1, "Fachada de la iglesia"))
            sliderItems.add(
                SlideModel(
                    R.drawable.sanpablo_slider2,
                    "Incendio en la iglesia en el año 1968"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.sanpablo_slider3,
                    "Interior de la iglesia"
                )
            )
        }
        if (place == "Santiago") {
            sliderItems.add(SlideModel(R.drawable.santiago_slider1, "Fotografía de la iglesia desde la calle Santiago"))
            sliderItems.add(
                SlideModel(
                    R.drawable.santiago_slider2,
                    "Fotografía de la iglesia en el siglo XX"
                )
            )
            sliderItems.add(
                SlideModel(
                    R.drawable.santiago_slider3,
                    "Imagen de la parte trasera de la iglesia"
                )
            )
        }
        areImagesAdded = true
    }
}
