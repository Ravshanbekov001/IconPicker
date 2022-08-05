package uz.futuresoft.iconpicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.maltaisn.icondialog.IconDialog
import com.maltaisn.icondialog.IconDialogSettings
import com.maltaisn.icondialog.data.Icon
import com.maltaisn.icondialog.pack.IconPack
import com.maltaisn.icondialog.pack.IconPackLoader
import com.maltaisn.iconpack.defaultpack.createDefaultIconPack
import uz.futuresoft.iconpicker.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), IconDialog.Callback {

    lateinit var binding: ActivityMainBinding
    var iconPack: IconPack? = null

    companion object {
        private const val ICON_DIALOG_TAG = "icon-dialog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadIconPicker()


        val iconDialog = supportFragmentManager.findFragmentByTag(ICON_DIALOG_TAG) as IconDialog?
            ?: IconDialog.newInstance(
                IconDialogSettings()
            )

        binding.showIconPicker.setOnClickListener {
            iconDialog.show(supportFragmentManager, ICON_DIALOG_TAG)
        }
    }

    private fun loadIconPicker() {
        val loader = IconPackLoader(this)

        val iconPack = createDefaultIconPack(loader = loader)
        iconPack.loadDrawables(loader.drawableLoader)

        this.iconPack = iconPack
    }

    override val iconDialogIconPack: IconPack?
        get() = iconPack

    override fun onIconDialogIconsSelected(dialog: IconDialog, icons: List<Icon>) {
        binding.selectedIcon.setImageDrawable(icons[0].drawable)
    }
}