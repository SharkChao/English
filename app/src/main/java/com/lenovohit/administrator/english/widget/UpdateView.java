/*
Copyright 2015 shizhefei（LuckyJayce）
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
   http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */
package com.lenovohit.administrator.english.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;

public abstract class UpdateView extends View {

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public UpdateView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
	}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public UpdateView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public UpdateView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public UpdateView(Context context) {
		super(context);
	}

	boolean mRequestedVisible = false;
	boolean mWindowVisibility = false;
	boolean mViewVisibility = false;
	private boolean mGlobalListenersAdded;
	final WindowManager.LayoutParams mLayout = new WindowManager.LayoutParams();

	@Override
	protected void onWindowVisibilityChanged(int visibility) {
		super.onWindowVisibilityChanged(visibility);
		mWindowVisibility = visibility == VISIBLE;
		mRequestedVisible = mWindowVisibility && mViewVisibility;
		// updateWindow(false, false);
	}

	@Override
	public void setVisibility(int visibility) {
		super.setVisibility(visibility);
		mViewVisibility = visibility == VISIBLE;
		boolean newRequestedVisible = mWindowVisibility && mViewVisibility;
		if (newRequestedVisible != mRequestedVisible) {
			// our base class (View) invalidates the layout only when
			// we go from/to the GONE state. However, SurfaceView needs
			// to request a re-layout when the visibility changes at all.
			// This is needed because the transparent region is computed
			// as part of the layout phase, and it changes (obviously) when
			// the visibility changes.
			requestLayout();
		}
		mRequestedVisible = newRequestedVisible;
		// updateWindow(false, false);
	}

	final ViewTreeObserver.OnScrollChangedListener mScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {

		@Override
		public void onScrollChanged() {
			updateWindow(false, false);
		}
	};

	private final ViewTreeObserver.OnPreDrawListener mDrawListener = new ViewTreeObserver.OnPreDrawListener() {
		@Override
		public boolean onPreDraw() {
			// reposition ourselves where the surface is
			// mHaveFrame = getWidth() > 0 && getHeight() > 0;
			// updateWindow(false, false);
			return true;
		}
	};

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		// mParent.requestTransparentRegion(this);
		// mSession = getWindowSession();
		mLayout.token = getWindowToken();
		mLayout.setTitle("SurfaceView");
		mViewVisibility = getVisibility() == VISIBLE;

		if (!mGlobalListenersAdded) {
			ViewTreeObserver observer = getViewTreeObserver();
			observer.addOnScrollChangedListener(mScrollChangedListener);
			observer.addOnPreDrawListener(mDrawListener);
			mGlobalListenersAdded = true;
		}
	}

	@Override
	protected void onDetachedFromWindow() {
		if (mGlobalListenersAdded) {
			ViewTreeObserver observer = getViewTreeObserver();
			observer.removeOnScrollChangedListener(mScrollChangedListener);
			observer.removeOnPreDrawListener(mDrawListener);
			mGlobalListenersAdded = false;
		}
		mRequestedVisible = false;
		updateWindow(false, false);
		mLayout.token = null;
		super.onDetachedFromWindow();
	}

	int[] mLocation = new int[2];
	boolean mVisible = false;
	int mLeft = -1;
	int mTop = -1;

	private boolean lock;

	protected void lock() {
		lock = true;
	}

	protected void unLock() {
		lock = false;
	}

	private void updateWindow(boolean force, boolean redrawNeeded) {
		if (lock) {
			return;
		}
		int[] tempLocationInWindow = new int[2];
		getLocationInWindow(tempLocationInWindow);
		final boolean visibleChanged = mVisible != mRequestedVisible;
		if (force || visibleChanged || tempLocationInWindow[0] != mLocation[0] || tempLocationInWindow[1] != mLocation[1] || redrawNeeded) {
			this.mLocation = tempLocationInWindow;
			Rect visiableRect = getVisiableRect();
			if (mVisiableRect == null || !mVisiableRect.equals(visiableRect)) {
				this.mVisiableRect = visiableRect;
				onUpdateWindow(visiableRect);
			}
		}
	}

	private Rect mVisiableRect;

	protected abstract void onUpdateWindow(Rect visiableRect);

	protected Rect getVisiableRect() {
		Rect visiableRect = new Rect();
		getGlobalVisibleRect(visiableRect);
		int[] location = new int[2];
		getLocationOnScreen(location);
		visiableRect.left = visiableRect.left - location[0];
		visiableRect.right = visiableRect.right - location[0];
		visiableRect.top = visiableRect.top - location[1];
		visiableRect.bottom = visiableRect.bottom - location[1];
		return visiableRect;
	}
}
