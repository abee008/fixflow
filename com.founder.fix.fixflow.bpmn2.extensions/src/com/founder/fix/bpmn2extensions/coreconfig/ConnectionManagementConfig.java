/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package com.founder.fix.bpmn2extensions.coreconfig;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Management Config</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementConfig#getSelected <em>Selected</em>}</li>
 *   <li>{@link com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementConfig#getConnectionManagementInstanceConfig <em>Connection Management Instance Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getConnectionManagementConfig()
 * @model extendedMetaData="name='connectionManagementConfig'"
 * @generated
 */
public interface ConnectionManagementConfig extends EObject {
	/**
	 * Returns the value of the '<em><b>Selected</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selected</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selected</em>' attribute.
	 * @see #setSelected(String)
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getConnectionManagementConfig_Selected()
	 * @model required="true"
	 *        extendedMetaData="name='selected' kind='attribute'"
	 * @generated
	 */
	String getSelected();

	/**
	 * Sets the value of the '{@link com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementConfig#getSelected <em>Selected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selected</em>' attribute.
	 * @see #getSelected()
	 * @generated
	 */
	void setSelected(String value);

	/**
	 * Returns the value of the '<em><b>Connection Management Instance Config</b></em>' containment reference list.
	 * The list contents are of type {@link com.founder.fix.bpmn2extensions.coreconfig.ConnectionManagementInstanceConfig}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Management Instance Config</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection Management Instance Config</em>' containment reference list.
	 * @see com.founder.fix.bpmn2extensions.coreconfig.CoreconfigPackage#getConnectionManagementConfig_ConnectionManagementInstanceConfig()
	 * @model containment="true"
	 *        extendedMetaData="name='connectionManagementInstanceConfig' kind='element'"
	 * @generated
	 */
	EList<ConnectionManagementInstanceConfig> getConnectionManagementInstanceConfig();

} // ConnectionManagementConfig
