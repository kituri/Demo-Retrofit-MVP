/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mvp.mobile.data.inject;

import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public final class Injector {

	static final String TAG = "Injector";

	@SuppressWarnings("unchecked")
	private static final void onSave(Field[] fields, Object obj, Bundle outsave) {
		if (fields == null || fields.length == 0)
			return;
		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(InjectData.class)) {
					field.setAccessible(true);
					Object value = field.get(obj);
					if (value instanceof String) {
						outsave.putString(field.getName(), (String) value);
					} else if (value instanceof Integer) {
						outsave.putInt(field.getName(), (Integer) value);
					} else if (value instanceof Parcelable) {
						outsave.putParcelable(field.getName(), (Parcelable) value);
					} else if (value instanceof Serializable) {
						outsave.putSerializable(field.getName(), (Serializable)value);
					} else if (value instanceof Float) {
						outsave.putFloat(field.getName(), (Float) value);
					} else if (value instanceof Double) {
						outsave.putDouble(field.getName(), (Double) value);
					} else if (value instanceof String[]) {
						outsave.putStringArray(field.getName(), (String[]) value);
					} else if (value instanceof Boolean) {
						outsave.putBoolean(field.getName(), (Boolean)value);
					} else if (value instanceof ArrayList<?>) {
						ArrayList<?> data = (ArrayList<?>) value;
						if (!data.isEmpty()) {
							Object temp = data.get(0);
							if (temp instanceof String) {
								outsave.putStringArrayList(field.getName(), (ArrayList<String>) value);
							} else if (temp instanceof Parcelable) {
								outsave.putParcelableArrayList(field.getName(), (ArrayList<Parcelable>) value);
							}
						}
					}
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void save(Object obj, Bundle outsave) {
		onSave(obj.getClass().getDeclaredFields(), obj, outsave);
		Class<? extends Object> _supperclass = obj.getClass().getSuperclass();
		while (_supperclass != null) {
			onSave(_supperclass.getDeclaredFields(), obj, outsave);
			_supperclass = _supperclass.getSuperclass();
		}
	}

	private static final void onRestore(Field[] fields, Object obj, Bundle savedInstanceState) {
		if (fields == null || fields.length == 0)
			return;
		try {
			for (Field field : fields) {
				if (field.isAnnotationPresent(InjectData.class)) {
					Object value = savedInstanceState.get(field.getName());
					field.setAccessible(true);
					field.set(obj, value);
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final void onRestore(Object obj, Bundle savedInstanceState) {
		onRestore(obj.getClass().getDeclaredFields(), obj, savedInstanceState);
		Class<? extends Object> _supperclass = obj.getClass().getSuperclass();
		while (_supperclass != null) {
			onRestore(_supperclass.getDeclaredFields(), obj, savedInstanceState);
			_supperclass = _supperclass.getSuperclass();
		}
	}

}
