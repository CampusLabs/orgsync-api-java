package com.orgsync.api;

import com.orgsync.api.messages.ApiError;

public abstract class ApiResponse<T> {

	public static final <T> ApiResponse<T> error(final ApiError error) {
		return new FailureResponse<T>(error);
	}

	@SuppressWarnings("unchecked")
	public static final <T> ApiResponse<T> success(final Object result) {
		// The cast is not awesome... but oh well
		return new SuccessResponse<T>((T) result);
	}

	public abstract boolean isSuccess();

	public T getResult() {
		return null;
	}

	public ApiError getError() {
		return null;
	}

	private static final class FailureResponse<T> extends ApiResponse<T> {
		private final ApiError error;

		public FailureResponse(final ApiError error) {
			super();
			this.error = error;
		}

		@Override
		public boolean isSuccess() {
			return false;
		}

		@Override
		public ApiError getError() {
			return error;
		}
	}

	private static final class SuccessResponse<T> extends ApiResponse<T> {
		private final T result;

		public SuccessResponse(final T result) {
			super();
			this.result = result;
		}

		@Override
		public boolean isSuccess() {
			return true;
		}

		@Override
		public T getResult() {
			return result;
		}
	}

}
