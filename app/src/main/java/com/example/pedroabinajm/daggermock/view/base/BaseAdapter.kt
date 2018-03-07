package com.example.pedroabinajm.daggermock.view.base

import android.annotation.SuppressLint
import android.databinding.DataBindingComponent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.AsyncTask
import android.support.annotation.MainThread
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.pedroabinajm.daggermock.BR

abstract class BaseAdapter<T : Any>(private val dataBindingComponent: DataBindingComponent? = null) :
        RecyclerView.Adapter<BaseAdapter<T>.ViewHolder>() {

    open var items: List<T>? = null
        protected set
    private var dataVersion = 0

    override fun onCreateViewHolder(parent: ViewGroup, layout: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = if (dataBindingComponent != null) {
            DataBindingUtil.inflate(layoutInflater, layout,
                    parent, false, dataBindingComponent)
        } else {
            DataBindingUtil.inflate(layoutInflater, layout,
                    parent, false)
        }
        return getViewHolderForLayout(layout, binding)
    }

    open fun getViewHolderForLayout(layout: Int, binding: ViewDataBinding) = ViewHolder(binding)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItemForPosition(position))
    }

    override fun getItemViewType(position: Int) = getLayoutIdForPosition(position)

    override fun getItemCount() = items?.size ?: 0

    @MainThread
    open fun replace(update: List<T>?) {
        dataVersion++
        when {
            items == null -> {
                if (update == null) return
                items = update
                notifyDataSetChanged()
            }
            update == null -> {
                val oldSize = items!!.size
                items = null
                notifyItemRangeRemoved(0, oldSize)
            }
            else -> Task(dataVersion, items!!, update).execute()
        }
    }

    open fun getItemForPosition(position: Int): Any = items!![position]

    abstract fun getLayoutIdForPosition(position: Int): Int

    open fun areItemsTheSame(oldItem: Any?, newItem: Any?) = oldItem == newItem

    open fun areContentsTheSame(oldItem: Any?, newItem: Any?) = oldItem == newItem
    open inner class ViewHolder(private val binding: ViewDataBinding) :
            RecyclerView.ViewHolder(binding.root) {

        open fun bind(o: Any) {
            binding.setVariable(BR.item, o)
            binding.executePendingBindings()
        }
    }

    @SuppressLint("StaticFieldLeak")
    inner class Task(
            private val startVersion: Int,
            private val oldItems: List<Any>,
            private val newItems: List<Any>
    ) : AsyncTask<Void, Void, DiffUtil.DiffResult>() {

        override fun doInBackground(vararg voids: Void?): DiffUtil.DiffResult =
                DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                    override fun getOldListSize() = oldItems.size

                    override fun getNewListSize() = newItems.size

                    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                            this@BaseAdapter.areItemsTheSame(
                                    oldItems[oldItemPosition],
                                    newItems[newItemPosition]
                            )

                    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                            this@BaseAdapter.areContentsTheSame(
                                    oldItems[oldItemPosition],
                                    newItems[newItemPosition]
                            )
                })

        override fun onPostExecute(result: DiffUtil.DiffResult) {
            if (startVersion == dataVersion) {
                @Suppress("UNCHECKED_CAST")
                items = newItems as List<T>
                result.dispatchUpdatesTo(this@BaseAdapter)
            }
        }
    }
}