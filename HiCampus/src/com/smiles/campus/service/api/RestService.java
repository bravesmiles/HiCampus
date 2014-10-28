/**
 * 
 */
package com.smiles.campus.service.api;

import com.baidu.mapapi.model.LatLng;

/**
 * @author yaojliu
 *
 */
public interface RestService {
	
	public LatLng getLocByUserId(String UserId);
	
	public LatLng getLocOfMine();
	
	public LatLng getLocOfMine(double lat, double lng);

}
