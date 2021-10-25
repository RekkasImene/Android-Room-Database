package fr.mastersid.rekkas.roomdatabase.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.mastersid.rekkas.roomdatabase.adapters.QuestionsListAdapter
import fr.mastersid.rekkas.roomdatabase.databinding.QuestionsFragmentBinding
import fr.mastersid.rekkas.roomdatabase.viewModels.QuestionsViewModel


@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private lateinit var _binding: QuestionsFragmentBinding
    private val questionModel: QuestionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuestionsFragmentBinding.inflate(inflater)
        return _binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionsListAdapter = QuestionsListAdapter()

        _binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = questionsListAdapter
        }

        _binding.refresh.setOnRefreshListener {
            questionModel.updateQuestionsList()
        }

        questionModel.questionList.observe(viewLifecycleOwner) { value ->
            _binding.refresh.isRefreshing = false
            questionsListAdapter.submitList(value)
        }


    }

}