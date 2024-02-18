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
 * The request is sent from the client to the server to resolve the subtypes for
 * a given type hierarchy item. Will return {@code null} if the server couldn't infer
 * a valid type from {@link #item}. The request doesn't define
 * its own client and server capabilities. It is only issued if a server registers for the
 * {@code textDocument/prepareTypeHierarchy} request.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class TypeHierarchySubtypesParams extends WorkDoneProgressAndPartialResultParams {
  /**
   * Representation of an item that carries type information.
   */
  @NonNull
  private TypeHierarchyItem item;

  public TypeHierarchySubtypesParams() {
  }

  public TypeHierarchySubtypesParams(@NonNull final TypeHierarchyItem item) {
    this.item = Preconditions.<TypeHierarchyItem>checkNotNull(item, "item");
  }

  /**
   * Representation of an item that carries type information.
   */
  @NonNull
  public TypeHierarchyItem getItem() {
    return this.item;
  }

  /**
   * Representation of an item that carries type information.
   */
  public void setItem(@NonNull final TypeHierarchyItem item) {
    this.item = Preconditions.checkNotNull(item, "item");
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("item", this.item);
    b.add("workDoneToken", getWorkDoneToken());
    b.add("partialResultToken", getPartialResultToken());
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
    TypeHierarchySubtypesParams other = (TypeHierarchySubtypesParams) obj;
    if (this.item == null) {
      if (other.item != null)
        return false;
    } else if (!this.item.equals(other.item))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    return 31 * super.hashCode() + ((this.item== null) ? 0 : this.item.hashCode());
  }
}
