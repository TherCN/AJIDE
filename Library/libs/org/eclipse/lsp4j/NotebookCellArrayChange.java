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

import java.util.List;
import org.eclipse.lsp4j.util.ToStringBuilder;

/**
 * A change describing how to move a `NotebookCell`
 * array from state S to S'.
 * <p>
 * Since 3.17.0
 */
@SuppressWarnings("all")
public class NotebookCellArrayChange {
  /**
   * The start offset of the cell that changed.
   */
  private int start;

  /**
   * The deleted cells
   */
  private int deleteCount;

  /**
   * The new cells, if any
   */
  private List<NotebookCell> cells;

  public NotebookCellArrayChange() {
  }

  public NotebookCellArrayChange(final int start, final int deleteCount) {
    this.start = start;
    this.deleteCount = deleteCount;
  }

  public NotebookCellArrayChange(final int start, final int deleteCount, final List<NotebookCell> cells) {
    this(start, deleteCount);
    this.cells = cells;
  }

  /**
   * The start offset of the cell that changed.
   */
  public int getStart() {
    return this.start;
  }

  /**
   * The start offset of the cell that changed.
   */
  public void setStart(final int start) {
    this.start = start;
  }

  /**
   * The deleted cells
   */
  public int getDeleteCount() {
    return this.deleteCount;
  }

  /**
   * The deleted cells
   */
  public void setDeleteCount(final int deleteCount) {
    this.deleteCount = deleteCount;
  }

  /**
   * The new cells, if any
   */
  public List<NotebookCell> getCells() {
    return this.cells;
  }

  /**
   * The new cells, if any
   */
  public void setCells(final List<NotebookCell> cells) {
    this.cells = cells;
  }

  @Override
  public String toString() {
    ToStringBuilder b = new ToStringBuilder(this);
    b.add("start", this.start);
    b.add("deleteCount", this.deleteCount);
    b.add("cells", this.cells);
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
    NotebookCellArrayChange other = (NotebookCellArrayChange) obj;
    if (other.start != this.start)
      return false;
    if (other.deleteCount != this.deleteCount)
      return false;
    if (this.cells == null) {
      if (other.cells != null)
        return false;
    } else if (!this.cells.equals(other.cells))
      return false;
    return true;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + this.start;
    result = prime * result + this.deleteCount;
    return prime * result + ((this.cells== null) ? 0 : this.cells.hashCode());
  }
}
