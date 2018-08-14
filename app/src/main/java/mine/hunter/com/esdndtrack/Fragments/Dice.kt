package mine.hunter.com.esdndtrack.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import mine.hunter.com.esdndtrack.R
import mine.hunter.com.esdndtrack.utilities.ArraySlider
import mine.hunter.com.esdndtrack.utilities.Dice
import mine.hunter.com.esdndtrack.utilities.StandardDice
import mine.hunter.com.esdndtrack.utilities.toIntOrZero

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Dice.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Dice.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Dice : Fragment()
{
	private var listener: OnFragmentInteractionListener? = null

	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)

	}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View?
	{
		val slider = ArraySlider(StandardDice.availableSides)
		val view = inflater.inflate(R.layout.dice_roller, container, false)

		val leftButton = view.findViewById<ImageButton>(R.id.DiceSwitchLeft)
		val rightButton = view.findViewById<ImageButton>(R.id.DiceSwitchRight)
		val diceInput = view.findViewById<EditText>(R.id.DiceSideInput)
		val numToRollView = view.findViewById<EditText>(R.id.NumberToRoll)
		val rollOutButton = view.findViewById<Button>(R.id.RollDiceButton)
		val resultView = view.findViewById<TextView>(R.id.RollResult)

		diceInput.setText("${slider.getCurrentItem()}")

		leftButton.setOnClickListener {
			diceInput.setText("${slider.moveLeft()}")
		}
		rightButton.setOnClickListener {
			diceInput.setText("${slider.moveRight()}")
		}

		rollOutButton.setOnClickListener {
			var numberToRoll = numToRollView.text.toIntOrZero()
			if (numberToRoll == 0)
			{
				numberToRoll = 1
			}
			resultView.text = Dice(diceInput.text.toIntOrZero()).rollMultiple(numberToRoll).toString()
		}

		return view
	}

	override fun onAttach(context: Context)
	{
		super.onAttach(context)
		if (context is OnFragmentInteractionListener)
		{
			listener = context
		} else
		{
			throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
		}
	}

	override fun onDetach()
	{
		super.onDetach()
		listener = null
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated
	 * to the activity and potentially other fragments contained in that
	 * activity.
	 *
	 *
	 * See the Android Training lesson [Communicating with Other Fragments]
	 * (http://developer.android.com/training/basics/fragments/communicating.html)
	 * for more information.
	 */
	interface OnFragmentInteractionListener
	{
		// TODO: Update argument type and name
		fun onFragmentInteraction(uri: Uri)
	}

	/**
	companion object
	{
		/**
		 * Use this factory method to create a new instance of
		 * this fragment using the provided parameters.
		 *
		 * @param param1 Parameter 1.
		 * @param param2 Parameter 2.
		 * @return A new instance of fragment Dice.
		 */
		// TODO: Rename and change types and number of parameters
		@JvmStatic
		fun newInstance(param1: String, param2: String) =
				Dice().apply {
					arguments = Bundle().apply {
						putString(ARG_PARAM1, param1)
						putString(ARG_PARAM2, param2)
					}
				}
	}
	**/
}
