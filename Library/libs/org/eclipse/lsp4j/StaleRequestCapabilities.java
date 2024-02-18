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

import java.util.ArrayList;
import java.util.List;
import org.eclipse.lsp4j.jsonrpc.validation.NonNull;
import org.eclipse.lsp4j.util.Preconditions;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * Client capability that signals how the client handles stale requests
 * (e.g. a request for which the client will not process the response
 * anymore since the information is outdated).
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class StaleRequestCapabilities {
  /**
   * The client will actively cancel the request.
   */
  private boolean cancel;

  /**
   * The list of requests for which the client will retry the request if it receives
   * a response with error code {@code ContentModified}
   */
  @NonNull
  private List<String> retryOnContentModified;

  public StaleRequestCapabilities() {
    ArrayList<String> _arrayList = new ArrayList<String>();
    this.retryOnContentModified = _arrayList;
  }

  public StaleRequestCapabilities(final boolean cancel, @NonNull final List<String> retryOnContentModified) {
    this.cancel = cancel;
    this.retryOnContentModified = Preconditions.<List<String>>checkNotNull(retryOnContentModified, "retryOnContentModified");
  }

  /**
   * The client will actively cancel the request.
   */
  public boolean isCancel() {
    return this.cancel;
  }

  /**
   * The client will actively cancel the request.
   */
  public void setCancel(final boolean cancel) {
    this.cancel = cancel;
  }

  /**
   * The list of requests for which the client will retry the request if it receives
   * a response with error code {@code ContentModified}
   */
  @NonNull
  public List<String> getRetryOnContentModified() {
    return this.retryOnContentModified;
  }

  /**
   * The list of requests for which the client will retry the request if it receives
   * a response with error code {@code ContentModified}
   */
  public void setRetryOnContentModified(@NonNull final List<String> retryOnContentModified) {
    this.retryOnContentModified = Preconditions.checkNotNull(retryOnContentModified, "retryOnContentModified");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("cancel", this.cancel);
    b.add("retryOnContentModified", this.retryOnContentModified);
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
    StaleRequestCapabilities other = (StaleRequestCapabilities) obj;
    if (other.cancel != this.cancel)
      return false;
    if (this.retryOnContentModified == null) {
      if (other.retryOnContentModified != null)
        return false;
    } else if (!this.retryOnContentModified.equals(other.retryOnContentModified))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (this.cancel ? 1231 : 1237);
    return prime * result + ((this.retryOnContentModified== null) ? 0 : this.retryOnContentModified.hashCode());
  }
}
