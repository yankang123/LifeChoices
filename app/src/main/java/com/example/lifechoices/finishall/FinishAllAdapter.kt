package com.example.lifechoices.finishall

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import androidx.recyclerview.widget.RecyclerView
import com.example.lifechoices.MainActivity
import com.example.lifechoices.MyApplication
import com.example.lifechoices.R
import com.example.lifechoices.roomb.AppDatabase
import com.example.lifechoices.roomb.AppDatabasefensh
import com.example.lifechoices.roomb.Item
import com.example.lifechoices.roomb.User

import java.util.Timer
import java.util.TimerTask


class FinishAllAdapter(private var list: List<Item>, val activity: MainActivity) :
    RecyclerView.Adapter<FinishAllAdapter.MyViewHolder>() {

    var itemDao = AppDatabase.getDatabase(MyApplication.getContext()).itemDao()
    var userDao = AppDatabasefensh.getDatabase(MyApplication.getContext()).itemDao()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val layout_ponit_hook = itemView.findViewById<LinearLayout>(R.id.layout_point_hook)
        val finishTextTwo = itemView.findViewById<TextView>(R.id.finish_text_two)
        val image_hook = itemView.findViewById<ImageView>(R.id.image_hook)
        var fenshutext = itemView.findViewById<TextView>(R.id.fenshu)
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item: Item = list.get(position);

        holder.finishTextTwo.setOnLongClickListener {
            itemDao.deleteById(list.get(position).text)
            var user =
                userDao.loadbyPosition(com.example.lifechoices.MyProgress.Item.position).get(0)
            user.fenshu -= item.meaning
            userDao.updateUser(user)
            Toast.makeText(
                MyApplication.getContext(),
                "删除" + itemDao.loadAllItems().size,
                Toast.LENGTH_SHORT
            ).show()
              list =itemDao.loadAllItemsByPosition(com.example.lifechoices.MyProgress.Item.position)
            activity.updateProgress()
            notifyDataSetChanged()

            true
        }
        //TODO("根据数据去  看是否已经完成 完成则划勾加划线")
        holder.fenshutext.text = "" + item.meaning
        holder.image_hook.visibility =
            if (item.isChecked == 1) View.VISIBLE else View.INVISIBLE
        holder.finishTextTwo.text = item.text
        holder.layout_ponit_hook.setOnClickListener {
            var user: User = User(0,com.example.lifechoices.MyProgress.Item.position);
            if (userDao.loadbyPosition(com.example.lifechoices.MyProgress.Item.position).size > 0) {
               user= userDao.loadbyPosition(com.example.lifechoices.MyProgress.Item.position).get(0)
            } else {
               userDao.insertUser(user)
            }
            if (item.isChecked == 0) {
                item.isChecked = 1
                user.fenshu += item.meaning
                holder.image_hook.visibility = View.INVISIBLE
            } else {
                holder.image_hook.visibility = View.VISIBLE
                item.isChecked = 0
                user.fenshu -= item.meaning
            }
            userDao.updateUser(user);
            Toast.makeText(
                activity,
                "" + userDao.loadbyPosition(com.example.lifechoices.MyProgress.Item.position)
                    .get(0).fenshu,
                Toast.LENGTH_SHORT
            ).show()
            itemDao.updateItem(item)
            activity.updateMainrecy(itemDao.loadAllItemsByPosition(com.example.lifechoices.MyProgress.Item.position))
            activity.updateProgress()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_finish_all, parent, false)
        return MyViewHolder(view)
    }

//   public  fun showSoftInput(){
//       var timer = Timer()
//       //TODO("activity 获取一次，这里又find 一次，似乎把原先的对象给夺过来了，所以，showdialog方法后，editdialog全是null")
//       val edit_dialog = activity.edit_dialog
//       //设置可获得焦点
//       edit_dialog?.isFocusable = true
//       edit_dialog?.isFocusableInTouchMode = true
//       edit_dialog?.requestFocus()
//       timer.schedule(object : TimerTask() {
//           override fun run() {
//
//               val inputManager: InputMethodManager =
//                   MyApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//
//               inputManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
//
//
//
//           }
//       }
//           , 300)
//
//   }
}