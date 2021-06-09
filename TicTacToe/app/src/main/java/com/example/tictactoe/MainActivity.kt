package com.example.tictactoe

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // For View Binding
    private lateinit var binding: ActivityMainBinding

    // Symbols Used for marking the cells
    private val symbol = "X"
    private val altSymbol = "0"
    private var nextSymbolX = true

    // To check if all cells are filled
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Sets up the click handlers
        setCellAction()
        binding.resultText.text = getString(R.string.welcome_text)
        setRestartAction()
    }

    private fun checkWinner() {

        // Row 1
        if(binding.grid1.text == binding.grid2.text && binding.grid2.text == binding.grid3.text && binding.grid2.text != "") {
            val result = "Winner: " + binding.grid1.text
            binding.resultText.text = result
            freezeCells()
        }

        // Row 2
        if(binding.grid4.text == binding.grid5.text && binding.grid5.text == binding.grid6.text && binding.grid5.text != "") {
            val result = "Winner: " + binding.grid5.text
            binding.resultText.text = result
            freezeCells()
        }

        // Row 3
        if(binding.grid7.text == binding.grid8.text && binding.grid8.text == binding.grid9.text && binding.grid9.text != "") {
            val result = "Winner: " + binding.grid8.text
            binding.resultText.text = result
            freezeCells()
        }

        // Column 1
        if(binding.grid1.text == binding.grid4.text && binding.grid4.text == binding.grid7.text && binding.grid7.text != "") {
            val result = "Winner: " + binding.grid4.text
            binding.resultText.text = result
            freezeCells()
        }

        // Column 2
        if(binding.grid2.text == binding.grid5.text && binding.grid5.text == binding.grid8.text && binding.grid8.text != "") {
            val result = "Winner: " + binding.grid5.text
            binding.resultText.text = result
            freezeCells()
        }

        // Column 3
        if(binding.grid3.text == binding.grid6.text && binding.grid6.text == binding.grid9.text && binding.grid9.text != "") {
            val result = "Winner: " + binding.grid6.text
            binding.resultText.text = result
            freezeCells()
        }

        // Diagonal 1
        if(binding.grid1.text == binding.grid5.text && binding.grid5.text == binding.grid9.text && binding.grid9.text != "") {
            val result = "Winner: " + binding.grid5.text
            binding.resultText.text = result
            freezeCells()
        }

        // Diagonal 2
        if(binding.grid3.text == binding.grid5.text && binding.grid5.text == binding.grid7.text && binding.grid7.text != "") {
            val result = "Winner: " + binding.grid5.text
            binding.resultText.text = result
            freezeCells()
        }
    }

    private fun freezeCells() {
        binding.grid1.isClickable = false
        binding.grid2.isClickable = false
        binding.grid3.isClickable = false
        binding.grid4.isClickable = false
        binding.grid5.isClickable = false
        binding.grid6.isClickable = false
        binding.grid7.isClickable = false
        binding.grid8.isClickable = false
        binding.grid9.isClickable = false
    }

    private fun setRestartAction() {
        binding.playButton.setOnClickListener {
            clearBoard()
            binding.resultText.text = getString(R.string.welcome_text)
            nextSymbolX = true
        }
    }

    private fun checkDraw() {
        if(counter == 9) {
            binding.resultText.text = getString(R.string.draw)
        }
    }

    private fun cellClickAction(view: TextView) {
        view.setOnClickListener {
            markCell(view)
        }
    }

    private fun setCellAction() {
        cellClickAction(binding.grid1)
        cellClickAction(binding.grid2)
        cellClickAction(binding.grid3)
        cellClickAction(binding.grid4)
        cellClickAction(binding.grid5)
        cellClickAction(binding.grid6)
        cellClickAction(binding.grid7)
        cellClickAction(binding.grid8)
        cellClickAction(binding.grid9)
    }

    private fun clearBoard() {
        clearCell(binding.grid1)
        clearCell(binding.grid2)
        clearCell(binding.grid3)
        clearCell(binding.grid4)
        clearCell(binding.grid5)
        clearCell(binding.grid6)
        clearCell(binding.grid7)
        clearCell(binding.grid8)
        clearCell(binding.grid9)
        counter = 0
    }

    private fun clearCell(cell: TextView) {
        cell.text = ""
        cell.isClickable = true
    }

    private fun changeResultText() {
        val temp = "Next: " + if(nextSymbolX) symbol else altSymbol
        binding.resultText.text = temp
    }

    private fun markCell(cell: TextView) {
        cell.text = if(nextSymbolX) symbol else altSymbol
        cell.isClickable = false
        counter += 1
        nextSymbolX = !nextSymbolX
        changeResultText()
        checkDraw()
        checkWinner()
    }
}