/**
 * 
 */
package com.smiles.campus.service;

import com.baidu.mapapi.model.LatLng;
import com.smiles.campus.service.api.RestService;

/**
 * @author yaojliu
 *
 */
public class RestServiceImpl implements RestService{

	@Override
	public LatLng getLocByUserId(String UserId) {
		// TODO Auto-generated method stub
		LatLng point = new LatLng(39.93923, 116.357428);  
		return point;
	}

	@Override
	public LatLng getLocOfMine() {
		// TODO Auto-generated method stub
		LatLng point = new LatLng(39.91923, 116.327428); 
		return point;
	}

	@Override
	public LatLng getLocOfMine(double lat, double lng) {
		// TODO Auto-generated method stub
		LatLng point = new LatLng(lat, lng); 
		return point;
	}
	

}
