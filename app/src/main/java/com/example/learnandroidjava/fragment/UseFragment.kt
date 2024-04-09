package com.example.learnandroidjava.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.learnandroidjava.R
import com.example.learnandroidjava.shared.intel.IFragmentActivityCommunicate

class UseFragment : Fragment() ,View.OnClickListener{
    private val tag: String = "mini_ocean"
    private var root: View? = null
    private var fragmentCallback: IFragmentActivityCommunicate? = null

    /**
     * 打开界面
     * onAttach-> onCreate -> onCreateView -> onActivityCreated -> onStart -> onResume
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        bundle?.getString("message")?.let {
            Log.i(tag, "activity 传递的数据: $it")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (root == null) root = inflater.inflate(R.layout.use_fragment, container, false)

        val button = root?.findViewById<Button>(R.id.use_fragment_btn)
        val tv = root?.findViewById<TextView>(R.id.use_fragment_tv)

        button?.setOnClickListener {
            tv?.text = "Fragment Button 被点击了"
        }

        val sendBtn= root?.findViewById<Button>(R.id.use_fragment_send_activity_btn)
        val receptionBtn = root?.findViewById<Button>(R.id.use_fragment_reception_activity_btn)
        sendBtn?.setOnClickListener(this)
        receptionBtn?.setOnClickListener(this)

        return root
    }

    /**
     * 重新打开界面
     * onStart -> onResume
     */
    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * 按下主屏键
     * onPause -> onStop
     * 按后退键
     * onPause -> onStop -> onDestroyView -> onDestroy -> onDetach
     */
    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    fun setCallback(fragmentCallback: IFragmentActivityCommunicate) {
        this.fragmentCallback = fragmentCallback
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.use_fragment_send_activity_btn -> {
                fragmentCallback?.sendToActivity("我是 fragment 传递的数据")
            }
            R.id.use_fragment_reception_activity_btn-> {
                val message = fragmentCallback?.getFromActivity("主动获取")
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

            }
        }
    }
}