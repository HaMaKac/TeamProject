package com.flowers.ui.plant

import com.flowers.R
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.flowers.MainActivity
import com.flowers.databinding.FragmentPlantBinding



class PlantFragment : Fragment() {


    override fun getContext(): Context? {
        return super.getContext()
    }

    private var _binding: FragmentPlantBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View {

        val plantViewModel =
            ViewModelProvider(this).get(PlantViewModel::class.java)

        _binding = FragmentPlantBinding.inflate(inflater, container, false)
        val root: View = binding.root




        //Timer

        binding.seekBar3.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar, progress: Int, fromUser: Boolean
            ) {
                setTextView()
                PrefUtil.setTimerLength(seekBar.progress, this@PlantFragment.requireContext())
                // write custom code when progress is changed
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                PrefUtil.setTimerLength(seekBar.progress, this@PlantFragment.requireContext())
                if(timerState == TimerState.Running){
                    timer.cancel()
                    onTimeChanged()
                    startTimer()
                    updateButtons()
                }
                else if (timerState == TimerState.Paused){
                    timer.cancel()
                    onTimeChanged()
                    timerState = TimerState.Paused;
                }
                else if(timerState == TimerState.Stopped){
                    timer.cancel()
                    onTimeChanged()
                    timerState = TimerState.Stopped
                }
            }
        })

        fun Set(minutes : Int, context: Context ){
            PrefUtil.setTimerLength(minutes, this.requireContext())
        }

        binding.fabPlay.setOnClickListener { v ->
        startTimer()
        timerState= TimerState.Running
        updateButtons()
    }
        binding.fabPause.setOnClickListener { v ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }

        binding.fabStop.setOnClickListener { v ->
                timer.cancel()
                onTimerFinished()
        }
        // /TIMER

        //////////////
        ///Placeholder flower, since buying them is not implemented yet
        class PlaceholderFlower(name: String, image : Int){
            val name = name
            val image = image
        }

        val flowers = mutableListOf<PlaceholderFlower>()
        for (i in 1..9) {
            if((activity as MainActivity).availableFlowers[i].isAvailable) {
                flowers.add(PlaceholderFlower((activity as MainActivity).availableFlowers[i].name,
                    (activity as MainActivity).availableFlowers[i].path))
            }
        }
        val flowersNames = mutableListOf<String>()
        flowers.iterator().forEach {
            flowers -> flowersNames.add(flowers.name)
        }
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, flowersNames)

        binding.boughtFlowers.adapter = arrayAdapter

        binding.boughtFlowers.setOnItemClickListener{adapterView, view, i, l ->
        Toast.makeText(requireContext(), flowers[i].name, Toast.LENGTH_LONG).show()
        binding.plantedPlant.setImageResource(flowers[i].image)}
        //////////////

        return root
    }

    //timer

    override fun onResume(){
        super.onResume()

        initTimer()

        //TODO: remove background timer

    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running){
            timer.cancel()
        }
        else if(timerState == TimerState.Paused){

        }
        PrefUtil.setPreviousTimerLengthSeconds(timerLengthSeconds,this.requireContext() )
        PrefUtil.setSecondsRemaining(secondsRemaining, this.requireContext())
        PrefUtil.setTimerState(timerState, this.requireContext())
    }

    private fun initTimer(){
        PrefUtil.setTimerLength(binding.seekBar3.progress, this.requireContext())
        timerState = PrefUtil.getTimerState(this.requireContext())

        if (timerState == TimerState.Stopped){
            //PrefUtil.setTimerLength(binding.seekBar3.progress, this.requireContext())
            setNewTimerLength()}
        else
            setPreviousTimerLength()

        secondsRemaining = if(timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(this.requireContext())
        else
            timerLengthSeconds

        //TODO: change secondsRemaining

        if(timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountDownUI()
    }

    private fun onTimerFinished(){
     timerState = TimerState.Stopped
        //Toast.makeText(activity, "You earned 5$!", Toast.LENGTH_SHORT).show()
        //updateBalance(5);
        //it works, but method onTimerFinished is not proper place for this
        //(it's executed during most operations of time change/stop/pause etc.)

        setNewTimerLength()

        binding.progressCountdown.progress = 0

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this.requireContext())
        secondsRemaining = timerLengthSeconds

        updateButtons()
        
        updateCountDownUI()

    }

    private fun onTimeChanged(){
        setNewTimerLength()

        binding.progressCountdown.progress = 0;

        PrefUtil.setSecondsRemaining(timerLengthSeconds, this.requireContext())
        secondsRemaining = timerLengthSeconds
    }

    private fun setTextView(){
        binding.textViewCountdown.text = (binding.seekBar3.progress.toString() + ':' + '0' + '0')
    }

    private fun startTimer(){
       // PrefUtil.setTimerLength(binding.seekBar3.progress, this.requireContext())
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000){
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
            }

        }.start()
        onAttach(this.requireContext())
    }

    private fun setNewTimerLength(){
        PrefUtil.setTimerLength(binding.seekBar3.progress, this.requireContext())
        val lenghtMinutes = PrefUtil.getTimerLength(this.requireContext())
        //PrefUtil.setTimerLenght(lenghtMinutes, this.requireContext())

        timerLengthSeconds = (lenghtMinutes * 60L)
        binding.progressCountdown.max = timerLengthSeconds.toInt()
    }

    private fun setPreviousTimerLength(){
        timerLengthSeconds = PrefUtil.getPreviousTimerLengthSeconds(this.requireContext())
        binding.progressCountdown.max = timerLengthSeconds.toInt()
    }

    private fun updateCountDownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        binding.textViewCountdown.text = "$minutesUntilFinished:${
            if (secondsStr.length == 2) secondsStr
            else "0" + secondsStr}"
        binding.progressCountdown.progress = (timerLengthSeconds - secondsRemaining).toInt()


    }

    private fun updateButtons(){
        when (timerState){
            TimerState.Running ->{
                binding.fabPlay.isEnabled = false
                binding.fabStop.isEnabled = true
                binding.fabPause.isEnabled = true
            }
            TimerState.Stopped ->{
                binding.fabPlay.isEnabled = true
                binding.fabStop.isEnabled = false
                binding.fabPause.isEnabled = false
            }
            TimerState.Paused->{
                binding.fabPlay.isEnabled = true
                binding.fabStop.isEnabled = true
                binding.fabPause.isEnabled = false
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    enum class TimerState{
        Stopped, Paused, Running
    }

    private var timer: CountDownTimer = object : CountDownTimer(0, 1000){
        override fun onFinish() = onTimerFinished()

        override fun onTick(millisUntilFinished: Long) {
            secondsRemaining = millisUntilFinished / 1000
            updateCountDownUI()

        }

    }
    private var timerLengthSeconds: Long = 0
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    Toast.makeText(activity, "Your flower will die, if you leave the app :(", Toast.LENGTH_SHORT).show()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
          callback
        )
    }

    fun updateBalance(amount : Int) { //provide amount to be added to the balance
        (activity as MainActivity).globalBalance += amount
    }
}