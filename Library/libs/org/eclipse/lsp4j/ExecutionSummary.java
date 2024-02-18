/**
 * Copyright (c) 2016-2018 TypeFox and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0,
 * or the Eclipse Distribution License v. 1.0 which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 * 
 * SPDX-License-Identifier: EPL-2.0 OR BSD-3-Clause
 */
package org.eclipse.lsp4j;

import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class ExecutionSummary {
  /**
   * A strict monotonically increasing value
   * indicating the execution order of a cell
   * inside a notebook.
   */
  private int executionOrder;

  /**
   * Whether the execution was successful or
   * not if known by the client.
   */
  private Boolean success;

  public ExecutionSummary() {
  }

  public ExecutionSummary(final int executionOrder) {
    this.executionOrder = executionOrder;
  }

  public ExecutionSummary(final int executionOrder, final Boolean success) {
    this(executionOrder);
    this.success = success;
  }

  /**
   * A strict monotonically increasing value
   * indicating the execution order of a cell
   * inside a notebook.
   */
  public int getExecutionOrder() {
    return this.executionOrder;
  }

  /**
   * A strict monotonically increasing value
   * indicating the execution order of a cell
   * inside a notebook.
   */
  public void setExecutionOrder(final int executionOrder) {
    this.executionOrder = executionOrder;
  }

  /**
   * Whether the execution was successful or
   * not if known by the client.
   */
  public Boolean getSuccess() {
    return this.success;
  }

  /**
   * Whether the execution was successful or
   * not if known by the client.
   */
  public void setSuccess(final Boolean success) {
    this.success = success;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("executionOrder", this.executionOrder);
    b.add("success", this.success);
    return b.toString();
  }

  @Override
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ExecutionSummary other = (ExecutionSummary) obj;
    if (other.executionOrder != this.executionOrder)
      return false;
    if (this.success == null) {
      if (other.success != null)
        return false;
    } else if (!this.success.equals(other.success))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.executionOrder;
    return prime * result + ((this.success== null) ? 0 : this.success.hashCode());
  }
}
