package com.siele.mkononiapptest.adapters

import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import com.siele.mkononiapptest.R
import com.siele.mkononiapptest.databinding.ChildItemBinding
import com.siele.mkononiapptest.databinding.GroupHeaderItemBinding
import com.siele.mkononiapptest.model.GroupHeader

class ExpandableListAdapter(
    private val titles: List<GroupHeader>,
    private val titleItems: Map<GroupHeader, List<String>>,
    private val context: Context
) : BaseExpandableListAdapter() {
    private val TAG = "Adapter"
    private val inflater = LayoutInflater.from(context)
    private lateinit var childItemBinding: ChildItemBinding
    private lateinit var groupHeaderBinding: GroupHeaderItemBinding

    override fun getGroupCount(): Int {
        Log.d(TAG, "getGroupCount: ${titles.size}")
        return titles.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        Log.d(TAG, "getChildrenCount: ${titleItems[titles[groupPosition]]!!.size} ")
        return titleItems[titles[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): GroupHeader  {
        Log.d(TAG, "getGroup: ${titles[groupPosition]}")
        return titles[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): String =
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
        groupHeaderBinding = GroupHeaderItemBinding.inflate(inflater)
        val groupHeader = getGroup(groupPosition)
            val cvtView = groupHeaderBinding.root
            groupHeaderBinding.apply {
                titleIcon.setImageResource(groupHeader.titleIcon)
                listTitle.text = groupHeader.title
                Log.d("Adapter::", "$groupHeader")
                Log.d("Adapter::", "titleHeader::${titles[1]} headerItems::${titleItems[groupHeader]}"
                )
                if (groupPosition==1){
                    listTitle.apply {
                        setCompoundDrawablesRelativeWithIntrinsicBounds(0,0,0,0)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        typeface = Typeface.DEFAULT_BOLD
                    }
                    titleIcon.visibility = View.GONE
                    root.isClickable = false
                }
                if (isExpanded && groupPosition!=1){
                    groupHeaderBinding.root.setBackgroundResource(R.color.white)
                }else{
                    groupHeaderBinding.root.setBackgroundResource(R.color.transparent)
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
        val holder: ChildViewHolder
        if (cvtView == null) {
            childItemBinding = ChildItemBinding.inflate(inflater)
            cvtView = childItemBinding.root
            holder = ChildViewHolder()
            holder.label = childItemBinding.expandedListItem
            cvtView.tag = holder
        } else {
            holder = cvtView.tag as ChildViewHolder
        }
        val listTitle = getChild(groupPosition, childPosition)
        holder.label!!.text = listTitle
        return cvtView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean = true

    inner class GroupHeaderViewHolder {
        var label: TextView? = null
        var itemIcon: ImageView? = null
    }

    inner class ChildViewHolder {
        var label: TextView? = null
    }


}