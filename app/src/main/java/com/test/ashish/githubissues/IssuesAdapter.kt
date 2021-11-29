package com.test.ashish.githubissues

import android.content.Context
import com.test.ashish.githubissues.Pojo.Issues
import android.widget.TextView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class IssuesAdapter(private val mContext: Context, private var mItems: List<Issues?>) :
    RecyclerView.Adapter<IssuesAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prNumberTextView: TextView
        val titleTextView: TextView
        val userTextView: TextView
        val patchUrlTextView: TextView
        val stateTextView: TextView

        init {
            prNumberTextView = itemView.findViewById<View>(R.id.prNumber) as TextView
            titleTextView = itemView.findViewById<View>(R.id.title) as TextView
            userTextView = itemView.findViewById<View>(R.id.userName) as TextView
            patchUrlTextView = itemView.findViewById<View>(R.id.patch_url) as TextView
            stateTextView = itemView.findViewById<View>(R.id.state) as TextView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val postView = inflater.inflate(R.layout.issues_item, parent, false)
        return ViewHolder(postView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]
        holder.prNumberTextView.text = item!!.prNumber.toString()
        holder.titleTextView.text = item!!.title
        holder.userTextView.text = item!!.user
        holder.patchUrlTextView.text = item!!.patchUrl
        holder.stateTextView.text = item!!.state
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    private fun getItem(adapterPosition: Int): Issues? {
        return mItems[adapterPosition]
    }

    fun resetList(issues: List<Issues?>) {
        mItems = issues
        notifyDataSetChanged()
    }
}