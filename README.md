# Android CMD-ANE for Adobe AIR apps

If you ever wished you could have access to Android CMD API from your AIR application, this is the ANE you were looking for.

* [Click here for ASDOC](https://myflashlab.github.io/asdoc/com/myflashlab/air/extensions/cmd/CMD.html)
* [See the ANE setup requirements](https://github.com/myflashlab/CMD-ANE/blob/master/src/ANE/extension.xml)

**IMPORTANT:** Implementing ANEs in your AIR projects means you may be required to add some [dependencies](https://github.com/myflashlab/common-dependencies-ANE) or copy some frameworks or editing your app's manifest file. Our ANE setup instruction is designed in a human-readable format but you may still need to familiarize yourself with this format. [Read this post for more information](https://www.myflashlabs.com/understanding-ane-setup-instruction/)

If you think manually setting up ANEs in your projects is confusing or time-consuming, you better check the [ANELAB Software](https://github.com/myflashlab/ANE-LAB/).

[![The ANE-LAB Software](https://www.myflashlabs.com/wp-content/uploads/2017/12/myflashlabs-ANE-LAB_features.jpg)](https://github.com/myflashlab/ANE-LAB/)

# AIR Usage #
```actionscript
import com.myflashlab.air.extensions.cmd.*;

// a simple cmd call
CMD.execute("/system/bin/ps", false, function ($output:String):void {
	trace($output);
});

// call the md5sum command on a file
CMD.execute("md5sum " + File.applicationDirectory.resolvePath("img.png").nativePath, false, function ($output:String):void {
	trace($output);
});
```
 
Are you using this ANE in your project? Maybe you'd like to buy us a beer :beer:?

[![paypal](https://www.paypalobjects.com/en_US/i/btn/btn_donateCC_LG.gif)](https://www.paypal.com/cgi-bin/webscr?cmd=_donations&business=payments@myflashlabs.com&lc=US&item_name=Donation+to+CMD+ANE&no_note=0&cn=&currency_code=USD&bn=PP-DonationsBF:btn_donateCC_LG.gif:NonHosted)

Add your name to the below list? Donate anything more than $100 and it will be.

## Sponsored by... ##
<table align="left">
    <tr>
        <td align="left"><img src="https://myflashlab.github.io/sponsors/digitans.de.jpg" width="40" height="40"></td>
        <td align="left"><a href="https://digitans.de">digitans.de</a><br>Production, development and design with passion</td>
    </tr>
</table>