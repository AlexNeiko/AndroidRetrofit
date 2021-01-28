package com.alexneiko.androidretrofitrestapi.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexneiko.androidretrofitrestapi.R
import com.alexneiko.androidretrofitrestapi.data.LocalUserDataStorageSPImpl
import com.alexneiko.androidretrofitrestapi.data.UserDataAdapter
import com.alexneiko.androidretrofitrestapi.model.UserAdapter
import com.alexneiko.androidretrofitrestapi.model.UserDTO
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var adapter : UserAdapter? = null
    private var userList : MutableList<UserDTO>? = null
    private var textView: TextView? = null



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        textView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView?.text = it
        })

        //First init Recycler View from local DB
        userList = LocalUserDataStorageSPImpl(root.context).getUsersFromDB().toMutableList()
        val recyclerView: RecyclerView = root.recyclerViewHomeFragment
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = UserAdapter(userList!!, root.context)
        recyclerView.adapter = adapter
        adapter?.notifyDataSetChanged()
        textView?.text = getString(R.string.loadData)


        //RX java rest api
        val fromDataSource = getObservableUserData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                adapter = UserAdapter(it, root.context)
                recyclerView.adapter = adapter
                adapter?.notifyDataSetChanged()
                textView?.text = getString(R.string.updatedNow)

            }, {
                textView?.visibility = View.VISIBLE
                recyclerView.layoutManager = LinearLayoutManager(this.context)
                adapter = UserAdapter(LocalUserDataStorageSPImpl(root.context).getUsersFromDB().toMutableList(), root.context)
                recyclerView.adapter = adapter
                adapter?.notifyDataSetChanged()
                textView?.text = getString(R.string.offlineMode) + LocalUserDataStorageSPImpl(root.context).getLastUpdateTime()


            }, {
            })

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun getObservableUserData() :Observable<MutableList<UserDTO>> {

        return Observable.create { subscriber->
            var userList = UserDataAdapter().getUsers() as MutableList<UserDTO>
            while (userList.size == 0) {
                Thread.sleep(500)
            }
            subscriber.onNext(userList)


        }
    }


}