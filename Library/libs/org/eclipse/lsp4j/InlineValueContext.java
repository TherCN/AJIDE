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
 * Additional information about the context in which inline values were
 * requested.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class InlineValueContext {
  /**
   * The stack frame (as a DAP Id) where the execution has stopped.
   */
  private int frameId;

  /**
   * The document range where execution has stopped.
   * Typically the end position of the range denotes the line where the
   * inline values are shown.
   */
  @NonNull
  private Range stoppedLocation;

  public InlineValueContext() {
  }

  public InlineValueContext(final int frameId, @NonNull final Range stoppedLocation) {
    this.frameId = frameId;
    this.stoppedLocation = Preconditions.<Range>checkNotNull(stoppedLocation, "stoppedLocation");
  }

  /**
   * The stack frame (as a DAP Id) where the execution has stopped.
   */
  public int getFrameId() {
    return this.frameId;
  }

  /**
   * The stack frame (as a DAP Id) where the execution has stopped.
   */
  public void setFrameId(final int frameId) {
    this.frameId = frameId;
  }

  /**
   * The document range where execution has stopped.
   * Typically the end position of the range denotes the line where the
   * inline values are shown.
   */
  @NonNull
  public Range getStoppedLocation() {
    return this.stoppedLocation;
  }

  /**
   * The document range where execution has stopped.
   * Typically the end position of the range denotes the line where the
   * inline values are shown.
   */
  public void setStoppedLocation(@NonNull final Range stoppedLocation) {
    this.stoppedLocation = Preconditions.checkNotNull(stoppedLocation, "stoppedLocation");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("frameId", this.frameId);
    b.add("stoppedLocation", this.stoppedLocation);
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
    InlineValueContext other = (InlineValueContext) obj;
    if (other.frameId != this.frameId)
      return false;
    if (this.stoppedLocation == null) {
      if (other.stoppedLocation != null)
        return false;
    } else if (!this.stoppedLocation.equals(other.stoppedLocation))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.frameId;
    return prime * result + ((this.stoppedLocation== null) ? 0 : this.stoppedLocation.hashCode());
  }
}
