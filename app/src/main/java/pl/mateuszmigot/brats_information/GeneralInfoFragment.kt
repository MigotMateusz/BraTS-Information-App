package pl.mateuszmigot.brats_information

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.mateuszmigot.brats_information.databinding.FragmentGeneralInfoBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GeneralInfoFragment : Fragment() {

    private var _binding: FragmentGeneralInfoBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGeneralInfoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        resources.configuration.setLocale(newConfig.locales.get(0))
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }
}