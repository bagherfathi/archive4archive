/**
 * 
 */
package com.ft.rfms.busi;

import java.io.Serializable;

import com.ft.rfms.entity.RfmsMerchant;
import com.ft.rfms.entity.RfmsMerchantBranch;
import com.ft.rfms.entity.RfmsMerchantPos;
import com.ft.rfms.entity.RfmsTicketBind;

/**
 * @author Administrator
 *
 */
public class PosBindDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -431781700135795397L;
	private RfmsTicketBind bind;
	private RfmsMerchantPos pos;
	private RfmsMerchantBranch branch;
	private RfmsMerchant merchant ;
	
	
	public PosBindDTO(RfmsTicketBind bind, RfmsMerchantPos pos, RfmsMerchantBranch branch, RfmsMerchant merchant) {
		super();
		this.bind = bind;
		this.pos = pos;
		this.branch = branch;
		this.merchant = merchant;
	}
	public PosBindDTO(RfmsMerchantPos pos, RfmsMerchantBranch branch, RfmsMerchant merchant) {
		super();
		this.pos = pos;
		this.branch = branch;
		this.merchant = merchant;
	}
	/**
	 * @return the bind
	 */
	public RfmsTicketBind getBind() {
		return bind;
	}
	/**
	 * @param bind the bind to set
	 */
	public void setBind(RfmsTicketBind bind) {
		this.bind = bind;
	}
	/**
	 * @return the branch
	 */
	public RfmsMerchantBranch getBranch() {
		return branch;
	}
	/**
	 * @param branch the branch to set
	 */
	public void setBranch(RfmsMerchantBranch branch) {
		this.branch = branch;
	}
	/**
	 * @return the merchant
	 */
	public RfmsMerchant getMerchant() {
		return merchant;
	}
	/**
	 * @param merchant the merchant to set
	 */
	public void setMerchant(RfmsMerchant merchant) {
		this.merchant = merchant;
	}
	/**
	 * @return the pos
	 */
	public RfmsMerchantPos getPos() {
		return pos;
	}
	/**
	 * @param pos the pos to set
	 */
	public void setPos(RfmsMerchantPos pos) {
		this.pos = pos;
	}
	
}
