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
 * Inlay hint options used during static or dynamic registration.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlayHintRegistrationOptions extends AbstractTextDocumentRegistrationAndWorkDoneProgressOptions {
  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also {@link Registration#id}.
   */
  private String id;

  /**
   * The server provides support to resolve additional
   * information for an inlay hint item.
   */
  private Boolean resolveProvider;

  public InlayHintRegistrationOptions() {
  }

  public InlayHintRegistrationOptions(final String id) {
    this.id = id;
  }

  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also {@link Registration#id}.
   */
  public String getId() {
    return this.id;
  }

  /**
   * The id used to register the request. The id can be used to deregister
   * the request again. See also {@link Registration#id}.
   */
  public void setId(final String id) {
    this.id = id;
  }

  /**
   * The server provides support to resolve additional
   * information for an inlay hint item.
   */
  public Boolean getResolveProvider() {
    return this.resolveProvider;
  }

  /**
   * The server provides support to resolve additional
   * information for an inlay hint item.
   */
  public void setResolveProvider(final Boolean resolveProvider) {
    this.resolveProvider = resolveProvider;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("id", this.id);
    b.add("resolveProvider", this.resolveProvider);
    b.add("workDoneProgress", getWorkDoneProgress());
    b.add("documentSelector", getDocumentSelector());
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
    if (!super.equals(obj))
      return false;
    InlayHintRegistrationOptions other = (InlayHintRegistrationOptions) obj;
    if (this.id == null) {
      if (other.id != null)
        return false;
    } else if (!this.id.equals(other.id))
      return false;
    if (this.resolveProvider == null) {
      if (other.resolveProvider != null)
        return false;
    } else if (!this.resolveProvider.equals(other.resolveProvider))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((this.id== null) ? 0 : this.id.hashCode());
    return prime * result + ((this.resolveProvider== null) ? 0 : this.resolveProvider.hashCode());
  }
}
