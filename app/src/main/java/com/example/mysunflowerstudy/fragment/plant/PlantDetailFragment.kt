package com.example.mysunflowerstudy.fragment.plant

import android.content.Intent
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ShareCompat
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.mysunflowerstudy.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.example.mysunflowerstudy.fragment.plant.PlantDetailFragment.Callback
import com.example.mysunflowerstudy.data.plant.Plant
import com.example.mysunflowerstudy.databinding.FragmentPlantDetailBinding
import com.example.mysunflowerstudy.fragment.base.BaseFragment2
import com.example.mysunflowerstudy.viewmodel.PlantDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlantDetailFragment : BaseFragment2<FragmentPlantDetailBinding>() {

    override val layoutResourceId: Int
        get() = R.layout.fragment_plant_detail

    private val plantDetailViewModel: PlantDetailViewModel by viewModels()

    override fun initAfterView() = binding.run {
        viewModel = plantDetailViewModel
        lifecycleOwner = viewLifecycleOwner
        callback = Callback { plant ->
            plant?.let {
                hideAppBarFab(fab)
                plantDetailViewModel.addPlantToGarden()
                Snackbar.make(root, R.string.added_plant_to_garden, Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        galleryNav.setOnClickListener { navigateToGallery() }

        var isToolbarShown = false

        // 스크롤 변경 리스너는 이미지가 완전히 축소 될 때 Y = 0에서 시작됩니다.
        plantDetailScrollview.setOnScrollChangeListener(
            NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->

                // 사용자가 이미지를 지나 도구 모음 높이로 스크롤하고 제목 텍스트가 도구 모음 아래에 있으므로 도구 모음이 표시되어야 합니다.
                val shouldShowToolbar = scrollY > toolbar.height

                // 도구 모음의 새 상태는 이전 상태와 다릅니다. AppBar 및 도구 모음 특성을 업데이트합니다.
                if (isToolbarShown != shouldShowToolbar) {
                    isToolbarShown = shouldShowToolbar

                    // 그림자 애니메이터를 사용하여 도구 모음이 표시되는 경우 고도 추가
                    appbar.isActivated = shouldShowToolbar

                    // 도구 모음이 표시되는 경우 식물 이름 표시
                    toolbarLayout.isTitleEnabled = shouldShowToolbar
                }
            }
        )

        toolbar.setNavigationOnClickListener { view ->
            view.findNavController().navigateUp()
        }

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_share -> {
                    createShareIntent()
                    true
                }
                else -> false
            }
        }
        setHasOptionsMenu(true)
    }

    private fun navigateToGallery() {
        plantDetailViewModel.plant.value?.let { plant ->
            val direction =
                PlantDetailFragmentDirections.actionPlantDetailFragmentToGalleryFragment(plant.name)
            findNavController().navigate(direction)
        }
    }

    // 공유 기능을 호출하기 위한 도우미 함수입니다.
    // 사용자가 공유 버튼/메뉴 항목을 누를 때 사용해야 합니다.
    @Suppress("DEPRECATION")
    private fun createShareIntent() {
        val shareText = plantDetailViewModel.plant.value.let { plant ->
            if (plant == null) {
                ""
            } else {
                getString(R.string.share_text_plant, plant.name)
            }
        }
        val shareIntent = ShareCompat.IntentBuilder.from(requireActivity())
            .setText(shareText)
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        startActivity(shareIntent)
    }

    // 앱 바 레이아웃에 고정된 부동 작업 단추의 표시 유형은 스크롤 위치에 의해 제어됩니다.
    // FAB를 클릭할 때 숨기기 위해 이 동작을 해제하려고 합니다.
    //
    // 이것은 Chris Banes의 스택 오버플로 답변에서 채택되었습니다. https://stackoverflow.com/a/41442923
    private fun hideAppBarFab(fab: FloatingActionButton) {
        val params = fab.layoutParams as CoordinatorLayout.LayoutParams
        val behavior = params.behavior as FloatingActionButton.Behavior
        behavior.isAutoHideEnabled = false
        fab.hide()
    }

    fun interface Callback {
        fun add(plant: Plant?)
    }
}
