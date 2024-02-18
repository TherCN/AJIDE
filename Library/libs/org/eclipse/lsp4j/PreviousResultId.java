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

import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A previous result id in a workspace pull request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class PreviousResultId {
  /**
   * The URI for which the client knows a
   * result id.
   */
  @NonNull
  private String uri;

  /**
   * The value of the previous result id.
   */
  @NonNull
  private String value;

  public PreviousResultId() {
  }

  public PreviousResultId(@NonNull final String uri, @NonNull final String value) {
    this.uri = Preconditions.<String>checkNotNull(uri, "uri");
    this.value = Preconditions.<String>checkNotNull(value, "value");
  }

  /**
   * The URI for which the client knows a
   * result id.
   */
  @NonNull
  public String getUri() {
    return this.uri;
  }

  /**
   * The URI for which the client knows a
   * result id.
   */
  public void setUri(@NonNull final String uri) {
    this.uri = Preconditions.checkNotNull(uri, "uri");
  }

  /**
   * The value of the previous result id.
   */
  @NonNull
  public String getValue() {
    return this.value;
  }

  /**
   * The value of the previous result id.
   */
  public void setValue(@NonNull final String value) {
    this.value = Preconditions.checkNotNull(value, "value");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("uri", this.uri);
    b.add("value", this.value);
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
    PreviousResultId other = (PreviousResultId) obj;
    if (this.uri == null) {
      if (other.uri != null)
        return false;
    } else if (!this.uri.equals(other.uri))
      return false;
    if (this.value == null) {
      if (other.value != null)
        return false;
    } else if (!this.value.equals(other.value))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.uri== null) ? 0 : this.uri.hashCode());
    return prime * result + ((this.value== null) ? 0 : this.value.hashCode());
  }
}
