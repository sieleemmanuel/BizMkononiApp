package com.siele.mkononiapptest.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.ArrayListItemBinding
import com.siele.mkononiapptest.databinding.TitleListItemBinding

class ExpandableListAdapter(
    private val titles: List<String>,
    private val titleItems: MutableMap<String, List<String>>,
    private val context: Context
) : BaseExpandableListAdapter() {
    private val inflater = LayoutInflater.from(context)
    private lateinit var arrayListItemBinding: ArrayListItemBinding
    private lateinit var titleListItemBinding: TitleListItemBinding

    override fun getGroupCount(): Int = titles.size

    override fun getChildrenCount(groupPosition: Int): Int =
        titleItems[titles[groupPosition]]!!.size

    override fun getGroup(groupPosition: Int): Any = titles[groupPosition]

    override fun getChild(groupPosition: Int, childPosition: Int): Any =
        titleItems[titles[groupPosition]]!![childPosition]

    override fun getGroupId(groupPosition: Int): Long = groupPosition.toLong()

    override fun getChildId(groupPosition: Int, childPosition: Int): Long =
        childPosition.toLong()

    override fun hasStableIds(): Boolean = false

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var cvtView = convertView
        val holder: TitleViewHolder
        if (cvtView == null) {
            titleListItemBinding = TitleListItemBinding.inflate(inflater)
            cvtView = titleListItemBinding.root
            holder = TitleViewHolder()
            holder.label = titleListItemBinding.listTitle
            cvtView.tag = holder
        } else {
            holder = cvtView.tag as TitleViewHolder
        }
        val listTitle = getGroup(groupPosition) as String
        holder.label!!.text = listTitle
        if (isExpanded && groupPosition!=1){
            titleListItemBinding.listTitle.background = AppCompatResources.getDrawable(context,R.color.white)
        }else{
            titleListItemBinding.listTitle.background = AppCompatResources.getDrawable(context,android.R.color.transparent)
        }
        titleListItemBinding.listTitle.apply {
            when (groupPosition) {
                0 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_insights,
                        0,
                        R.drawable.ic_arrow_drop,
                        0
                    )
                }
                1 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        0,
                        0,
                        0,
                        0
                    )
                }
                2 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_businesses,
                        0,
                        R.drawable.ic_arrow_drop,
                        0
                    )
                }


                3 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_cart,
                        0,
                        R.drawable.ic_arrow_drop,
                        0
                    )
                }
                4 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_supply,
                        0,
                        R.drawable.ic_arrow_drop,
                        0
                    )
                }
                5 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_money,
                        0,
                        R.drawable.ic_arrow_drop,
                        0
                    )
                }
                6 -> {
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_customers,
                        0,
                        R.drawable.ic_arrow_drop,
                        0
                    )
                }
            }
        }
        return cvtView
    }


    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var cvtView = convertView
        val holder: TitleItemsViewHolder
        if (cvtView == null) {
            arrayListItemBinding = ArrayListItemBinding.inflate(inflater)
            cvtView = arrayListItemBinding.root
            holder = TitleItemsViewHolder()
            holder.label = arrayListItemBinding.expandedListItem
            cvtView.tag = holder
        } else {
            holder = cvtView.tag as TitleItemsViewHolder
        }
        val listTitle = getChild(groupPosition, childPosition) as String
        holder.label!!.text = listTitle
        return cvtView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    inner class TitleViewHolder {
        var label: TextView? = null
    }

    inner class TitleItemsViewHolder {
        var label: TextView? = null
    }


}