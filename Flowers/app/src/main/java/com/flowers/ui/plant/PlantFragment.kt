package com.flowers.ui.plant

import com.flowers.R
import android.widget.TextView
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
                PrefUtil.setTimerLenght(seekBar.progress, this@PlantFragment.requireContext())
                // write custom code when progress is changed
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                PrefUtil.setTimerLenght(seekBar.progress, this@PlantFragment.requireContext())
            }
        })

        fun Set(minutes : Int, context: Context ){
            PrefUtil.setTimerLenght(minutes, this.requireContext())
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

        val flowers = mutableListOf(PlaceholderFlower("Lilly of the valley", R.drawable.konwalie_plant),PlaceholderFlower("Lilly", R.drawable.lilia_plant), PlaceholderFlower("Forget-me-not", R.drawable.niezapominajka_plant), PlaceholderFlower("Rose", R.drawable.roza_plant))
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

        //TODO: removve bacground timer

    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running){
            timer.cancel()
        }
        else if(timerState == TimerState.Paused){

        }
        PrefUtil.setPreviousTimerLenghtSeconds(timerLenghtSeconds,this.requireContext() )
        PrefUtil.setSecondsRemaining(secondsRemaining, this.requireContext())
        PrefUtil.setTimerState(timerState, this.requireContext())
    }

    private fun initTimer(){
        PrefUtil.setTimerLenght(binding.seekBar3.progress, this.requireContext())
        timerState = PrefUtil.getTimerState(this.requireContext())

        if (timerState == TimerState.Stopped){
            //PrefUtil.setTimerLenght(binding.seekBar3.progress, this.requireContext())
            setNewTimerLenght()}
        else
            setPreviousTimerLenght()

        secondsRemaining = if(timerState == TimerState.Running || timerState == TimerState.Paused)
            PrefUtil.getSecondsRemaining(this.requireContext())
        else
            timerLenghtSeconds

        //TODO: change secondsRemaining

        if(timerState == TimerState.Running)
            startTimer()

        updateButtons()
        updateCountDownUI()
    }

    private fun onTimerFinished(){
     timerState = TimerState.Stopped

        setNewTimerLenght()

        binding.progressCountdown.progress = 0

        PrefUtil.setSecondsRemaining(timerLenghtSeconds, this.requireContext())
        secondsRemaining = timerLenghtSeconds

        updateButtons()
        updateCountDownUI()

    }
    private fun setTextView(){
        binding.textViewCountdown.text = (binding.seekBar3.progress.toString() + ':' + '0' + '0')
    }

    private fun startTimer(){
       // PrefUtil.setTimerLenght(binding.seekBar3.progress, this.requireContext())
        timerState = TimerState.Running

        timer = object : CountDownTimer(secondsRemaining * 1000, 1000){
            override fun onFinish() = onTimerFinished()

            override fun onTick(millisUntilFinished: Long) {
                secondsRemaining = millisUntilFinished / 1000
                updateCountDownUI()
            }

        }.start()
    }

    private fun setNewTimerLenght(){
        PrefUtil.setTimerLenght(binding.seekBar3.progress, this.requireContext())
        val lenghtMinutes = PrefUtil.getTimerLength(this.requireContext())
        //PrefUtil.setTimerLenght(lenghtMinutes, this.requireContext())

        timerLenghtSeconds = (lenghtMinutes * 60L)
        binding.progressCountdown.max = timerLenghtSeconds.toInt()
    }

    private fun setPreviousTimerLenght(){
        timerLenghtSeconds = PrefUtil.getPreviousTimerLenghtSeconds(this.requireContext())
        binding.progressCountdown.max = timerLenghtSeconds.toInt()
    }

    private fun updateCountDownUI() {
        val minutesUntilFinished = secondsRemaining / 60
        val secondsInMinuteUntilFinished = secondsRemaining - minutesUntilFinished * 60
        val secondsStr = secondsInMinuteUntilFinished.toString()
        binding.textViewCountdown.text = "$minutesUntilFinished:${
            if (secondsStr.length == 2) secondsStr
            else "0" + secondsStr}"
        binding.progressCountdown.progress = (timerLenghtSeconds - secondsRemaining).toInt()


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

    private lateinit var timer: CountDownTimer
    private var timerLenghtSeconds: Long = 0
    private var timerState = TimerState.Stopped

    private var secondsRemaining = 0L
}