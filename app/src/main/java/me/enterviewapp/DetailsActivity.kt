package me.enterviewapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class DetailsActivity : AppCompatActivity() {
  private val titleDetails = findViewById<TextView>(R.id.titleDetails)
  private val descdetails = findViewById<TextView>(R.id.descdetails)
  private val priceDetails = findViewById<TextView>(R.id.priceDetails)
  private val pagesDetails = findViewById<TextView>(R.id.pagesDetails)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_details)

    if (intent != null) {
      val result: Result? = intent.extras!!.getParcelable(RESULT_KEY)

      SetAsViews(result)
      formatDateSetText(result!!)

      ButterKnife.bind(this)

    }
  }

  private fun SetAsViews(result: Result?) {
    titleDetails.text = result!!.title
    descdetails.text = result.description
    priceDetails.text = result.prices!![0].price
    pagesDetails.text = result.pageCount
  }

  //função para formatar a data
  private fun formatDateSetText(result: Result) {
    try {
      val local = Locale("pt", "BR")
      val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
      val outputFormat: DateFormat = SimpleDateFormat("MMMM ',' dd ',' yyyy", local)
    } catch (ex: Exception) {
      Log.i("Exception", "------------> " + ex.message)
    }
  }

  class DetailsAdapter(private var listresult: List<Result>) :
    RecyclerView.Adapter<DetailsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view =
        LayoutInflater.from(parent.context).inflate(R.layout.itemlayout_card, parent, false)
      return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val resultlist = listresult[position]
      holder.onBind(resultlist)
    }

    override fun getItemCount(): Int {
      return listresult.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      private val imageView: ImageView
      private val textViewName: TextView

      init {
        itemView.apply {
          imageView = findViewById(R.id.imagelogo)
          textViewName = findViewById(R.id.textName)
        }
      }

      fun onBind(result: Result) {
        textViewName.text = result.title
      }
    }
  }

  companion object{
    const val RESULT_KEY = "result"
  }
}
