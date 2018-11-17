package com.myflashlab.air.extensions.cmd
{
import flash.external.ExtensionContext;
import flash.system.Capabilities;
import flash.utils.getDefinitionByName;
import flash.utils.getQualifiedClassName;
import flash.events.StatusEvent;

/**
 *
 * @author Hadi Tavakoli - 9/1/2018 5:45 PM
 *
 */
public class CMD
{
	public static const ANDROID:String = "android";
	public static const IOS:String = "ios";
	
	private var OverrideClass:Class;
	
	private static var _ex:CMD;
	public static const EXTENSION_ID:String = "com.myflashlab.air.extensions.cmd";
	public static const VERSION:String = "1.0.1";
	
	private var _context:ExtensionContext;
	private var _os:String;
	
	private var _callback:Function;
	
	/** @private */
	public function CMD():void
	{
		OverrideClass = getDefinitionByName("com.myflashlab.air.extensions.dependency.OverrideAir") as Class;
		OverrideClass["applyToAneLab"](getQualifiedClassName(this));
		
		// find the current running OS
		if(Capabilities.manufacturer.toLowerCase().indexOf(IOS) > -1) _os = IOS;
		else if(Capabilities.manufacturer.toLowerCase().indexOf(ANDROID) > -1) _os = ANDROID;
		else _os = "desktop";
		
		if(_os == "desktop" || _os == IOS) return;
		
		// initialize the context
		_context = ExtensionContext.createExtensionContext(EXTENSION_ID, null);
		_context.addEventListener(StatusEvent.STATUS, onStatus);
		
		if(CMD.DEMO_ANE) _context.call("command", "isTestVersion");
	}
	
	// ---------------------------------------------------------------------------------------- methods
	
	/**
	 *
	 * @param $command
	 * @param $root
	 * @param $callback		expects one parameter as String
	 */
	public static function execute($command:String, $root:Boolean, $callback:Function):void
	{
		if(!_ex) _ex = new CMD();
		
		_ex._callback = $callback;
		
		_ex._context.call("command", "toExecute", $command, $root);
	}
	
	// --------------------------------------------------------------------------------------- private
	
	private function onStatus(e:StatusEvent):void
	{
		switch(e.code)
		{
				case CMDEvents.OUTPUT:
					
					if(_callback != null)
					{
						_callback(e.level);
						_callback = null;
					}
				
					
				break;
		}
	}
	
	// ---------------------------------------------------------------------------------------- properties
	
	
	
	// ------------------------------------------------------------------------------------------------------------------------------------ Check Club Member
	
	/** @private */
	internal static const DEMO_ANE:Boolean = false;
}
}