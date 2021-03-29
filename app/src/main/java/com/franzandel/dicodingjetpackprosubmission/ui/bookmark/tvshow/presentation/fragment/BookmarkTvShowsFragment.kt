package com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.franzandel.dicodingjetpackprosubmission.R
import com.franzandel.dicodingjetpackprosubmission.base.BaseFragmentVM
import com.franzandel.dicodingjetpackprosubmission.databinding.FragmentBookmarkTvShowsBinding
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.data.entity.BookmarkTvShowResponse
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.adapter.BookmarkTvShowsAdapter
import com.franzandel.dicodingjetpackprosubmission.ui.bookmark.tvshow.presentation.viewmodel.BookmarkTvShowsVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Created by Franz Andel on 29/03/21.
 * Android Engineer
 */

@AndroidEntryPoint
class BookmarkTvShowsFragment :
    BaseFragmentVM<BookmarkTvShowsVM, FragmentBookmarkTvShowsBinding>() {

    @Inject
    lateinit var bookmarkTvShowsVM: BookmarkTvShowsVM

    private lateinit var deletedBookmarkTvShowResponse: BookmarkTvShowResponse

    override fun getVM(): BookmarkTvShowsVM = bookmarkTvShowsVM

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookmarkTvShowsBinding =
        FragmentBookmarkTvShowsBinding.inflate(inflater, container, false)

    override fun onFragmentCreated() {
        setupObservers()
        bookmarkTvShowsVM.getBookmarkMovies()
    }

    private fun setupObservers() {
        bookmarkTvShowsVM.bookmarkTvShows.observe(
            viewLifecycleOwner,
            Observer { bookmarkMoviesDTO ->
                setupRV(bookmarkMoviesDTO)
            })

        bookmarkTvShowsVM.deleteBookmarkResult.observe(
            viewLifecycleOwner,
            Observer {
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
                    bookmarkTvShowsVM.addMovieToBookmark(deletedBookmarkTvShowResponse)
                }
                snackbar.show()
            })

        bookmarkTvShowsVM.addBookmarkResult.observe(
            viewLifecycleOwner,
            Observer {
                Snackbar.make(
                    requireView(),
                    getString(R.string.bookmark_deleted_undo_success),
                    Snackbar.LENGTH_SHORT
                ).show()
            })
    }

    private fun setupRV(bookmarkTvShowsResponse: List<BookmarkTvShowResponse>) {
        val adapter = BookmarkTvShowsAdapter(requireContext()) { bookmarkTvShowResponse ->
            showDeleteConfirmationDialog(bookmarkTvShowResponse)
        }
        viewBinding.rvBookmarkTvShows.adapter = adapter
        adapter.submitList(bookmarkTvShowsResponse)
    }

    private fun showDeleteConfirmationDialog(bookmarkTvShowResponse: BookmarkTvShowResponse) {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.bookmark_confirmation_delete_tv_show_title))
            .setMessage(getString(R.string.bookmark_confirmation_delete_description))
            .setNegativeButton(getString(R.string.bookmark_confirmation_negative_button), null)
            .setPositiveButton(getString(R.string.bookmark_confirmation_positive_button)) { _, _ ->
                deletedBookmarkTvShowResponse = bookmarkTvShowResponse
                bookmarkTvShowsVM.deleteMovieFromBookmark(bookmarkTvShowResponse.id)
            }
            .show()
    }
}