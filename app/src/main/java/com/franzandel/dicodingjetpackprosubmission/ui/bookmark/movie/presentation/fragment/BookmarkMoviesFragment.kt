package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentBookmarkMoviesBinding
import com.franzandel.dicodingjetpackprosubmission.external.extension.showShareMessage
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.BookmarkActivity
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.SortChoice
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.data.entity.BookmarkMovieResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.adapter.BookmarkMoviesAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.movie.presentation.viewmodel.BookmarkMoviesVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BookmarkMoviesFragment : BaseFragmentVM<BookmarkMoviesVM, FragmentBookmarkMoviesBinding>() {

    @Inject
    lateinit var bookmarkMoviesVM: BookmarkMoviesVM

    private lateinit var deletedBookmarkMovieResponse: BookmarkMovieResponse

    private val currentActivity by lazy {
        requireActivity() as BookmarkActivity
    }

    override fun getVM(): BookmarkMoviesVM = bookmarkMoviesVM

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkMoviesBinding =
        FragmentBookmarkMoviesBinding.inflate(inflater, container, false)

    override fun onFragmentCreated() {
        setupListeners()
        setupObservers()
        bookmarkMoviesVM.getBookmarkMovies(SortChoice.TITLE)
    }

    private fun setupListeners() {
        currentActivity.binding.mtbBookmark.setOnMenuItemClickListener { menuItem ->
            menuItem.isChecked = true

            when (menuItem.itemId) {
                R.id.menu_share -> {
                    requireActivity().showShareMessage()
                    true
                }
                R.id.menu_sort_title -> {
                    bookmarkMoviesVM.getBookmarkMovies(SortChoice.TITLE)
                    true
                }
                R.id.menu_sort_rating -> {
                    bookmarkMoviesVM.getBookmarkMovies(SortChoice.RATING)
                    true
                }
                else -> false
            }
        }
    }

    private fun setupObservers() {
        bookmarkMoviesVM.bookmarkMovies.observe(viewLifecycleOwner, Observer { bookmarkMoviesDTO ->
            setupRV(bookmarkMoviesDTO)
        })

        bookmarkMoviesVM.deleteBookmarkResult.observe(viewLifecycleOwner, Observer {
            Snackbar.make(
                requireView(),
                getString(R.string.bookmark_deleted),
                Snackbar.LENGTH_SHORT
            ).show()

            val snackbar = Snackbar.make(
                requireView(),
                getString(R.string.bookmark_deleted),
                Snackbar.LENGTH_SHORT
            )
            snackbar.setAction(getString(R.string.bookmark_deleted_undo)) {
                bookmarkMoviesVM.addMovieToBookmark(deletedBookmarkMovieResponse)
            }
            snackbar.show()
        })

        bookmarkMoviesVM.addBookmarkResult.observe(viewLifecycleOwner, Observer {
            Snackbar.make(
                requireView(),
                getString(R.string.bookmark_deleted_undo_success),
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    private fun setupRV(bookmarkMoviesResponse: PagedList<BookmarkMovieResponse>) {
        val adapter = BookmarkMoviesAdapter { bookmarkMovieResponse ->
            showDeleteConfirmationDialog(bookmarkMovieResponse)
        }
        viewBinding.rvBookmarkMovies.adapter = adapter
        adapter.submitList(bookmarkMoviesResponse)
    }

    private fun showDeleteConfirmationDialog(bookmarkMovieResponse: BookmarkMovieResponse) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.bookmark_confirmation_delete_movie_title))
            .setMessage(getString(R.string.bookmark_confirmation_delete_description))
            .setNegativeButton(getString(R.string.bookmark_confirmation_negative_button), null)
            .setPositiveButton(getString(R.string.bookmark_confirmation_positive_button)) { _, _ ->
                deletedBookmarkMovieResponse = bookmarkMovieResponse
                bookmarkMoviesVM.deleteMovieFromBookmark(bookmarkMovieResponse.id)
            }
            .show()
    }
}