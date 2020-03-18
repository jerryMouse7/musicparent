package com.liu.passport.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

public interface UserDef {

	/**
	 * 用户状态
	 */
	enum State {

		// @formatter:off
        ACHIEVED(0,"已归档"),
        NORMAL(1, "启用中"),
		BAN(2,"已禁用"),
		;
        // @formatter:on

		private Integer code;

		private String name;

		State(Integer code, String name) {
			this.code = code;
			this.name = name;
		}

		private static Map<Integer, State> mapping = ((Supplier<Map<Integer, State>>) () -> {
			Map<Integer, State> mapping = new HashMap<>();

			for (UserDef.State value : UserDef.State.values()) {
				mapping.put(value.getCode(), value);
			}

			return mapping;
		}).get();

		public static UserDef.State from(final Integer code) {

			UserDef.State result = mapping.get(code);

			if (Objects.nonNull(result)) {
				return result;
			} else {
				return NORMAL;
			}
		}

		public boolean match(final Integer code) {
			return this.code.equals(code);
		}

		public Integer getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

	/**
	 * 用户类型
	 */
	enum Type {

		// @formatter:off
        ADMIN(1,"管理员"),
		GENERAL_USER(2,"普通用户"),
		SUPER_ADMIN(3, "超级管理员")
        ;
        // @formatter:on

		private Integer code;

		private String name;

		Type(Integer code, String name) {
			this.code = code;
			this.name = name;
		}

		private static Map<Integer, Type> mapping = ((Supplier<Map<Integer, Type>>) () -> {
			Map<Integer, Type> mapping = new HashMap<>();

			for (UserDef.Type value : UserDef.Type.values()) {
				mapping.put(value.getCode(), value);
			}

			return mapping;
		}).get();

		public static UserDef.Type from(final Integer code) {

			UserDef.Type result = mapping.get(code);

			if (Objects.nonNull(result)) {
				return result;
			} else {
				return Type.GENERAL_USER;
			}
		}

		public boolean match(final Integer code) {
			return this.code.equals(code);
		}

		public Integer getCode() {
			return code;
		}

		public String getName() {
			return name;
		}
	}

}
