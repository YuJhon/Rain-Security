package com.jhon.rain.rbac.repository;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.spi.MetadataBuildingContext;

/**
 * <p>功能描述</br> Hibernate的命名规则的修改 </p>
 *
 * @author jiangy19
 * @version v1.0
 * @FileName RainImplicitNamingStrategy
 * @date 2017/11/2 11:10
 */
public class RainImplicitNamingStrategy extends ImplicitNamingStrategyJpaCompliantImpl {

	private static final long serialVersionUID = -1783408418594257134L;

	private static final String TABLE_PREFIX = "t_rain_";

	@Override
	protected Identifier toIdentifier(String stringForm, MetadataBuildingContext buildingContext) {
		return super.toIdentifier(TABLE_PREFIX + "" + stringForm.toLowerCase(), buildingContext);
	}
}
