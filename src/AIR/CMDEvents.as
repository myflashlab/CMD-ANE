package com.myflashlab.air.extensions.cmd
{
import flash.events.Event;

/**
 * @private
 *
 * @author Hadi Tavakoli - 9/1/2018 5:45 PM
 */
public class CMDEvents extends Event
{
	public static const OUTPUT:String = "onOutPut";
	
	private var _output:String;
	
	/**
	 * @private
	 *
	 * @param $type
	 * @param $output
	 */
	public function CMDEvents($type:String, $output:String):void
	{
		_output = $output;
		
		super($type, false, false);
	}
	
	public function get output():String
	{
		return _output;
	}
}
	
}